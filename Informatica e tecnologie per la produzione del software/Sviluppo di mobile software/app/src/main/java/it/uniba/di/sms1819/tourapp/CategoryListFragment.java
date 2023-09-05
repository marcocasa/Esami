package it.uniba.di.sms1819.tourapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import it.uniba.di.sms1819.tourapp.Adapters.CategoryCardAdapter;
import it.uniba.di.sms1819.tourapp.Adapters.SpacingItemDecorator;
import it.uniba.di.sms1819.tourapp.Models.Category;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class CategoryListFragment extends Fragment {

    RecyclerView recyclerView;
    CategoryCardAdapter adapter;
    LocationManager locationManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category_list, container, false);
    }
    private OnCompleteListener<QuerySnapshot> onGetCategoriesListener = new OnCompleteListener<QuerySnapshot>() {
        @Override
        public void onComplete(@NonNull Task<QuerySnapshot> task) {
            if (!task.isSuccessful()) {
                Common.logError(task.getException().toString());
                return;
            }

            // salvo tutte le categorie nell'array (se non è stato già fatto prima)
            if (Instance.categoryList.size() == 0) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    // salvo oggetto categoria e id all'interno della categoria stessa. serve dopo per avviare l'intent verso la categoria
                    Category category = document.toObject(Category.class);
                    category.id = document.getId();

                    Instance.categoryList.add(category);
                }
            }

            // shared preferences non esiste, attivo tutto di default
            SharedPreferences preferences = getDefaultSharedPreferences(getActivity());
            if (!preferences.contains("monument")) {
                SharedPreferences.Editor editor = preferences.edit();
                for (Category category : Instance.categoryList) {
                    editor.putBoolean(category.id, true);
                }
                editor.apply();
            }

            // due colonne
            GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
            recyclerView.setLayoutManager(layoutManager);

            adapter = new CategoryCardAdapter(Instance.categoryList);

            // spaziatura tra le card visualizzate
            recyclerView.addItemDecoration(new SpacingItemDecorator(getContext(), R.dimen.space_5));

            // associo l'azione di apertura della categoria quando clicco su una card
            adapter.setOnCategoryClickListener(new CategoryCardAdapter.OnCategoryListener() {
                @Override
                public void onCategoryClicked(Category category, int position) {

                    // se posso accedere al gps e sono in attesa della posizione chiedo all'utente di attendere
                    if (Common.canAccessGPS(locationManager, getContext()) && Instance.lastKnownLocation == null) {
                        Toast.makeText(getContext(), getString(R.string.wait_for_location), Toast.LENGTH_SHORT).show();
                        return;
                    }

                    CategoryFragment categoryFragment = new CategoryFragment();

                    assert getActivity() != null;

                    Bundle bundle = new Bundle();
                    bundle.putString("category", category.id);

                    categoryFragment.setArguments(bundle);
                    FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();


                    fragmentTransaction.replace(R.id.content, categoryFragment)
                            .addToBackStack(null)
                            .commit();

                }
            });
            recyclerView.setAdapter(adapter);
        }
    };

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
    }

    @Override
    public void onStart() {
        super.onStart();

        // Carico le categorie
        Instance.db.collection("categories")
                .get()
                .addOnCompleteListener(onGetCategoriesListener);
    }
}
