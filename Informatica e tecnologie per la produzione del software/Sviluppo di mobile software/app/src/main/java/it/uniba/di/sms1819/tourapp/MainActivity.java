package it.uniba.di.sms1819.tourapp;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.model.Image;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.uniba.di.sms1819.tourapp.Models.User;

public class MainActivity extends AppCompatActivity implements LocationListener {

    private DrawerLayout drawer;
    private TextView userNameTextView;
    private TextView userCreditTextView;
    private MenuItem adminMenu, mapMenuItem, lastCheckedMenuItem, loginMenuItem, logoutMenuItem, addCreditMenuItem, receiveCreditMenuItem, addPlaceMenuItem, transactionsMenuItem;
    private LocationManager locationManager;
    private Fragment activeFragment;
    private boolean uiUpdated = false;
    private boolean alreadyAskedPermission = false;
    ArrayList<Integer> menuBackStack = new ArrayList<>();
    FragmentManager fragmentManager = getSupportFragmentManager();
    NavigationView nDrawer;
    Menu navMenu;
    private boolean shownHighPrecisionPopUp = false;
    private NavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            // imposto l'elemento come selezionato nel menu
            menuItem.setChecked(true);

            // deseleziono l'ultima voce selezionata dal menu
            if (lastCheckedMenuItem != null) {
                lastCheckedMenuItem.setChecked(false);
            }

            lastCheckedMenuItem = menuItem;
            // chiudiamo il drawer navigation
            drawer.closeDrawers();

            switch (menuItem.getItemId()) {
                case R.id.nav_home:
                    loadFragment(CategoryListFragment.class);
                    break;
                case R.id.nav_add_place:
                    loadFragment(AddPlaceFragment.class);
                    break;
                case R.id.nav_saved_places:
                    loadFragment(SavedPlacesFragment.class);
                    break;
                case R.id.nav_map:
                    // non consentire di aprire la mappa se non ho prima ottenuto la posizione
                    if (Instance.lastKnownLocation != null) {
                        loadFragment(MapFragment.class);
                    } else {
                        Toast.makeText(getApplicationContext(), getString(R.string.wait_for_location), Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.nav_preferences:
                    loadFragment(SettingsFragment.class);
                    break;
                case R.id.nav_add_credit:
                    loadFragment(AddCreditFragment.class);
                    break;
                case R.id.nav_login:
                    doUserLogin();
                    break;
                case R.id.nav_transactions:
                    loadFragment(TransactionListFragment.class);
                    break;
                case R.id.nav_logout:
                    doUserLogout();
                    break;
                case R.id.nav_receive_credit:
                    Intent intent = new Intent(getApplicationContext(), ReceiveCreditActivity.class);
                    startActivity(intent);
                default:
                    loadFragment(CategoryListFragment.class);
                    break;
            }

            return true;
        }
    };

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        savedInstanceState.putBoolean("shownHighPrecisionPopUp", shownHighPrecisionPopUp);
        savedInstanceState.putBoolean("alreadyAskedPermission", alreadyAskedPermission);
    }

    EventListener<DocumentSnapshot> userInfoListener = new EventListener<DocumentSnapshot>() {
        @Override
        public void onEvent(@javax.annotation.Nullable DocumentSnapshot documentSnapshot, @javax.annotation.Nullable FirebaseFirestoreException e) {
            Instance.userInfo = documentSnapshot.toObject(User.class);

            // crea l'utente se non esiste
            if (Instance.userInfo == null) {
                User newUser = new User();
                newUser.credit = 0.0;
                newUser.email = Instance.user.getEmail();
                newUser.is_admin = false;

                Instance.db.collection("users").document(Instance.user.getUid()).set(newUser);

                Instance.userInfo = newUser;
            }

            // aggiorna credito nella UI
            userCreditTextView.setText(String.format(getString(R.string.credit), String.valueOf(Instance.userInfo.credit)));


            // ho ricevuto i dati, aggiorno l'interfaccia una sola volta
            if (!uiUpdated) {
                Common.logDebug("Utente loggato:" + Instance.userInfo.email);
                Common.logDebug("Utente is_admin:" + Instance.userInfo.is_admin);
                updateUserUI();
                uiUpdated = true;
            }

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);

        // non chiedere di nuovo accesso alla posizione se l'ho già chiesto in precedenza
        if (savedInstanceState != null) {
            alreadyAskedPermission = savedInstanceState.getBoolean("alreadyAskedPermission");
            shownHighPrecisionPopUp = savedInstanceState.getBoolean("shownHighPrecisionPopUp");
        }

        // drawer navigation
        nDrawer = findViewById(R.id.nav_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.parent_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        nDrawer.setNavigationItemSelectedListener(navigationItemSelectedListener);

        // elementi ui
        final View headerView = nDrawer.getHeaderView(0);
        userNameTextView = findViewById(R.id.user_name);
        navMenu = nDrawer.getMenu();
        loginMenuItem = navMenu.findItem(R.id.nav_login);
        logoutMenuItem = navMenu.findItem(R.id.nav_logout);
        addCreditMenuItem = navMenu.findItem(R.id.nav_add_credit);
        receiveCreditMenuItem = navMenu.findItem(R.id.nav_receive_credit);
        userCreditTextView = headerView.findViewById(R.id.user_credit);
        userNameTextView = headerView.findViewById(R.id.user_name);
        addPlaceMenuItem = navMenu.findItem(R.id.nav_add_place);
        transactionsMenuItem = navMenu.findItem(R.id.nav_transactions);
        mapMenuItem = navMenu.findItem(R.id.nav_map);
        adminMenu = navMenu.findItem(R.id.admin_menu);
        lastCheckedMenuItem = null;

        // imposto il fragment iniziale
        loadFragment(CategoryListFragment.class);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (!shownHighPrecisionPopUp && !locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            showPopUpMessage(R.string.gps_message_2);
            shownHighPrecisionPopUp = true;
        }

        // se non ho già chiesto il permesso per la posizione lo chiedo
        if (!Common.canAccessGPS(locationManager, getApplicationContext()) && !alreadyAskedPermission) {
            Common.logDebug("Non ho già chiesto il permesso per la posizione, lo chiedo");
            //showPopUpMessage(R.string.gps_info);
            showPopUpMessage(R.string.gps_info);
        } else {
            // ottengo la posizione
            requestLocationUpdate();
        }

        // carica dati utente (se era già loggato)
        startUserListeners();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // requestCode = LOGIN UTENTE
        if (requestCode == Constants.USER_LOGGED_IN) {
            if (resultCode == RESULT_OK) {
                // Successfully signed in
                IdpResponse response = IdpResponse.fromResultIntent(data);
                Instance.user = FirebaseAuth.getInstance().getCurrentUser();

                assert Instance.user != null;
                Log.d(Constants.TAG, "User ID: " + Instance.user.getUid());

                // avvia i listener per le informazioni utente
                startUserListeners();
                updateUserUI();

                // messaggio di benvenuto
                Toast.makeText(this, getString(R.string.welcome), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, getString(R.string.login_failed), Toast.LENGTH_SHORT).show();
            }
        }

        if (activeFragment.getClass() == AddPlaceFragment.class && ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            Image image = ImagePicker.getFirstImageOrNull(data);

            if (image != null) {
                ((AddPlaceFragment) activeFragment).setImageFromActivityResult(image);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        // vai indietro solo se ci sono altri fragment nello stack
        FragmentManager fragmentManager = getSupportFragmentManager();
        Log.d(Constants.TAG, "Fragment BackStack Size: " + fragmentManager.getBackStackEntryCount());

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
        } else {
            if (fragmentManager.getBackStackEntryCount() > 1) {

                if (lastCheckedMenuItem != null) {
                    lastCheckedMenuItem.setChecked(false);
                }

                fragmentManager.popBackStack();
            } else {
                finish();
            }
        }


    }

    private void showPopUpMessage(int message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setCancelable(true);
        builder.setTitle(R.string.warning);
        builder.setMessage(getString(message));

        if (message == R.string.gps_info) {
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            Constants.REQUEST_LOCATION);
                }
            });
        } else {
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
        }

        builder.show();
    }

    private void startUserListeners() {
        if (Instance.user == null) {
            return;
        }

        // avvia listener per scaricare dati utente
        Instance.db.collection("users")
                .document(Instance.user.getUid())
                .addSnapshotListener(userInfoListener);
    }

    private void updateUserUI() {
        // se ha il gps attivo mostra mappa
        mapMenuItem.setVisible(Common.canAccessGPS(locationManager, getApplicationContext()));

        // aggiorna menu
        if (Instance.user == null) {
            loginMenuItem.setVisible(true);
            logoutMenuItem.setVisible(false);
            transactionsMenuItem.setVisible(false);

            // nascondi credito ed email quando sloggato
            userCreditTextView.setVisibility(View.GONE);
            userNameTextView.setVisibility(View.GONE);

            adminMenu.setVisible(false);
        } else {
            loginMenuItem.setVisible(false);
            transactionsMenuItem.setVisible(true);
            logoutMenuItem.setVisible(true);

            // mostra credito e email
            userCreditTextView.setVisibility(View.VISIBLE);
            userNameTextView.setText(Instance.user.getEmail());
            userNameTextView.setVisibility(View.VISIBLE);

            if (Instance.userInfo != null) {
                // mostra item menu admin
                if (Instance.userInfo.is_admin) {
                    adminMenu.setVisible(true);
                    // se ha il gps attivo consenti l'aggiunta di un posto
                    addPlaceMenuItem.setVisible(Common.canAccessGPS(locationManager, getApplicationContext()));
                }
            }

        }
    }

    public void loadFragment(Class fragmentClass) {
        Fragment fragment;

        try {
            // instanziare fragment
            fragment = (Fragment) fragmentClass.newInstance();
            // sostituiamo il contenuto col nuovo fragment

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content, fragment);
            fragmentTransaction.addToBackStack(fragmentClass.getName());
            fragmentTransaction.commit();

            activeFragment = fragment;
        } catch (Exception e) {
            Common.logError("Errore nella creazione del fragment: " + e.getMessage());
        }
    }

    private void doUserLogin() {
        // Imposta i provider per l'autenticazione (solo email!)
        List<AuthUI.IdpConfig> providers = Collections.singletonList(
                new AuthUI.IdpConfig.EmailBuilder().build()
        );

        // Crea e lancia l'intent per il login
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                Constants.USER_LOGGED_IN);
    }

    private void doUserLogout() {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        Instance.userInfo = null;
                        Instance.user = null;
                        uiUpdated = false;

                        // ricarica parte UI utente
                        updateUserUI();

                        // svuota stack fragment
                        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

                        //  carica lista categorie (homepage)
                        loadFragment(CategoryListFragment.class);

                        Toast.makeText(getApplicationContext(), getString(R.string.logged_out), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        if (requestCode == Constants.REQUEST_LOCATION) {
            alreadyAskedPermission = true;

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Common.logDebug("Permesso posizione consentito");
                requestLocationUpdate();
                updateUserUI();
            } else {
                Common.logDebug("Permesso posizione negato");
                showPopUpMessage(R.string.gps_message_1);
                updateUserUI();
            }
        }
    }

    private void requestLocationUpdate() {
        try {
            locationManager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, this, getMainLooper());
            updateUserUI();
        } catch (SecurityException e) {
            Common.logError(e.getMessage());
        }
    }


    @Override
    public void onLocationChanged(Location location) {
        Instance.lastKnownLocation = location;
        Common.logDebug("Nuova posizione: " + location.getLatitude() + ", " + location.getLongitude());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {
        //
    }
}
