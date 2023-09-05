(function(window, undefined) {
  var dictionary = {
    "db5566c1-1f45-41a5-ba36-12966377561e": "CategoriaPizzeria",
    "81981096-7ab7-4545-a766-c24abce8ef60": "riepilogoAcquisti",
    "8e5abb11-4cac-4de6-9d5d-fd756e3642e7": "Scrivi",
    "c550f57f-e007-43a8-93fd-850f79d28883": "TutorialPreferenze",
    "67270b71-c077-4833-882f-78201efd3514": "Notifiche",
    "7801dfac-7a14-43f2-b665-58f248a89f3b": "HomeNew",
    "38660f32-e16a-40f7-b49c-5bf23eba3272": "Coupon",
    "b9c06432-ed96-4458-a48b-79865d62f6f5": "ListaAcquisti",
    "42caed59-35ad-42f3-8d1f-dedc05c1384f": "indicazioniMappa",
    "81545e6d-9fa5-4e3e-8d30-b2ccadcc5b45": "Birthday",
    "681e83e5-7933-4757-86bd-a95cbee04712": "Login4",
    "26c309f8-cd31-4d30-8f25-89e1e0458fee": "MappaVuota2",
    "b70d00ef-0167-42a7-bafd-2e4e7c9bf7ac": "Login3",
    "89a3d237-90c2-4796-ac2c-3b0c53413f63": "Lista desideri",
    "2d5a9997-4819-408f-93f7-56d7ad815c0a": "Login2",
    "204408b9-3022-44ae-9b3e-fe98ea58a65c": "Fotocamera2",
    "bd98c769-34e2-4e50-abdf-1150c88473b2": "ScarpeNike",
    "3dc5b94c-4c39-465c-b138-a44900eef55a": "Login",
    "1e8f05f4-3ced-4df6-970f-0240420921c9": "CarteFedeltà",
    "03b38d2b-b877-4da5-bfa3-ca41c98702e7": "VediTutte",
    "06a3aabc-9ad6-458d-a09c-2d74870b7ef2": "carteAcquisto",
    "8928c13a-1aa9-47cd-bcf1-723bfe4df0bb": "Fotocamera",
    "18209a6a-a252-4318-b9c6-acff5b4f88d4": "Mappa",
    "7f1f97e4-8193-4cc7-8462-3ce809af458c": "CambiaPreferenze",
    "7cdba5d1-d484-46eb-92cc-d1ef3bd61c0d": "ModificaDati",
    "00275739-e229-4b9d-a259-87863ff27812": "Ricerca",
    "19dced2a-b6f4-4633-8eeb-b297bd92d3f0": "Home",
    "3f9c4093-b490-408b-8c2c-2cc7eaf9b10d": "MappaVuota",
    "6e58cd6c-d30c-498a-9e7b-503ad60ae464": "Carte",
    "0f0f1df9-517c-4f88-a099-d643fa62a62f": "Profilo",
    "13b49caa-32d9-4d6a-a693-99c86f3c6bca": "MappaCerchiata",
    "c4c89749-c854-4455-870b-fd1dbee53f4e": "RisultatiRicerca",
    "f39803f7-df02-4169-93eb-7547fb8c961a": "Telefono android",
    "0b9856ab-a65e-42b1-8adc-d8ec53a30acc": "Categoria_Biglietti",
    "16e7f65c-7c83-461e-84ec-df214e6390e3": "Barra di ricerca",
    "08451681-5f74-428d-97f0-174aebfbce0d": "Categoria_Ristorazione",
    "bb8abf58-f55e-472d-af05-a7d1bb0cc014": "default"
  };

  var uriRE = /^(\/#)?(screens|templates|masters|scenarios)\/(.*)(\.html)?/;
  window.lookUpURL = function(fragment) {
    var matches = uriRE.exec(fragment || "") || [],
        folder = matches[2] || "",
        canvas = matches[3] || "",
        name, url;
    if(dictionary.hasOwnProperty(canvas)) { /* search by name */
      url = folder + "/" + canvas;
    }
    return url;
  };

  window.lookUpName = function(fragment) {
    var matches = uriRE.exec(fragment || "") || [],
        folder = matches[2] || "",
        canvas = matches[3] || "",
        name, canvasName;
    if(dictionary.hasOwnProperty(canvas)) { /* search by name */
      canvasName = dictionary[canvas];
    }
    return canvasName;
  };
})(window);