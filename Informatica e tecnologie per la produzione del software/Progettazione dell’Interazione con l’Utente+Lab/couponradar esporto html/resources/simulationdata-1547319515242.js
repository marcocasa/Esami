function initData() {
  jimData.variables["detersivi"] = "celeste";
  jimData.variables["ricambiAuto"] = "celeste";
  jimData.variables["ideeRegalo"] = "celeste";
  jimData.variables["preferenzeSelezionate"] = "0";
  jimData.variables["prodottiFarmaceutici"] = "celeste";
  jimData.variables["outlet"] = "celeste";
  jimData.variables["Contacts-var"] = "";
  jimData.variables["cibo"] = "celeste";
  jimData.variables["vestiti"] = "celeste";
  jimData.variables["fastFood"] = "celeste";
  jimData.datamasters["Cibo"] = [
    {
      "id": 1,
      "datamaster": "Cibo",
      "userdata": {
        "immagine": "./images/aa0fd374-75c3-47a6-b655-a1f80ff6f5ca.jpg",
        "testo": "Patatine in omaggio"
      }
    },
    {
      "id": 2,
      "datamaster": "Cibo",
      "userdata": {
        "immagine": "./images/001b713c-8af7-4e30-a7a4-35b84f38f400.jpg",
        "testo": "Menu McNugget 3.99\u20ac"
      }
    },
    {
      "id": 3,
      "datamaster": "Cibo",
      "userdata": {
        "immagine": "./images/24fb2815-00d9-4afb-a691-2d98970bcc3d.jpg",
        "testo": "Panino e bibita a soli 6.98\u20ac"
      }
    }
  ];

  jimData.datamasters["Suggeriti"] = [
    {
      "id": 1,
      "datamaster": "Suggeriti",
      "userdata": {
        "immagine": "./images/2189fc36-2a3c-4a55-909c-a9ed7a157086.jpg",
        "testo": "Prova il nuovo menu!"
      }
    },
    {
      "id": 2,
      "datamaster": "Suggeriti",
      "userdata": {
        "immagine": "./images/f883b11d-80cf-4bc1-a319-9b1c23b9b779.jpg",
        "testo": "Nike Air 123"
      }
    }
  ];

  jimData.datamasters["Vestiti"] = [
    {
      "id": 1,
      "datamaster": "Vestiti",
      "userdata": {
        "immagine": "./images/6861149f-83aa-43fb-a34a-1ceedf1102ca.jpg",
        "testo": "3 al prezo di 1"
      }
    },
    {
      "id": 2,
      "datamaster": "Vestiti",
      "userdata": {
        "immagine": "./images/893fc73a-e673-4355-9b67-be22851ce970.jpg",
        "testo": "-25% Adidas Outlet"
      }
    },
    {
      "id": 3,
      "datamaster": "Vestiti",
      "userdata": {
        "immagine": "./images/ac324bf5-05e1-472e-8cf1-d2a642ca98ac.jpg",
        "testo": "Berretto omaggio (min 50\u20ac spesa)"
      }
    }
  ];

  jimData.isInitialized = true;
}