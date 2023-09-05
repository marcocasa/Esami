jQuery("#simulation")
  .on("click", ".s-681e83e5-7933-4757-86bd-a95cbee04712 .click", function(event, data) {
    var jEvent, jFirer, cases;
    if(data === undefined) { data = event; }
    jEvent = jimEvent(event);
    jFirer = jEvent.getEventFirer();
    if(jFirer.is("#s-Ellipse_6")) {
      cases = [
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-681e83e5-7933-4757-86bd-a95cbee04712 #s-Ellipse_6": {
                      "attributes": {
                        "background-color": "#3B5A9D",
                        "background-attachment": "scroll"
                      }
                    }
                  },{
                    "#s-681e83e5-7933-4757-86bd-a95cbee04712 #s-Ellipse_6": {
                      "attributes-ie": {
                        "-pie-background": "#3B5A9D",
                        "-pie-poll": "false"
                      }
                    }
                  } ],
                  "exectype": "serial",
                  "delay": 0
                }
              ]
            }
          ],
          "exectype": "serial",
          "delay": 0
        },
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-681e83e5-7933-4757-86bd-a95cbee04712 #s-Rectangle_1 > .backgroundLayer": {
                      "attributes": {
                        "background-color": "#B05C45",
                        "background-attachment": "scroll"
                      }
                    }
                  },{
                    "#s-681e83e5-7933-4757-86bd-a95cbee04712 #s-Rectangle_1": {
                      "attributes-ie": {
                        "-pie-background": "#B05C45",
                        "-pie-poll": "false"
                      }
                    }
                  } ],
                  "exectype": "serial",
                  "delay": 0
                }
              ]
            }
          ],
          "exectype": "serial",
          "delay": 0
        }
      ];
      event.data = data;
      jEvent.launchCases(cases);
    } else if(jFirer.is("#s-Rectangle_1")) {
      cases = [
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimNavigation",
                  "parameter": {
                    "target": "screens/7801dfac-7a14-43f2-b665-58f248a89f3b",
                    "transition": {
                      "type": "slidedown",
                      "duration": 700
                    }
                  },
                  "exectype": "serial",
                  "delay": 0
                }
              ]
            }
          ],
          "exectype": "serial",
          "delay": 0
        }
      ];
      event.data = data;
      jEvent.launchCases(cases);
    } else if(jFirer.is("#s-Image_43")) {
      cases = [
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimNavigation",
                  "parameter": {
                    "isbackward": true,
                    "transition": {
                      "type": "slideright",
                      "duration": 700
                    }
                  },
                  "exectype": "serial",
                  "delay": 0
                }
              ]
            }
          ],
          "exectype": "serial",
          "delay": 0
        }
      ];
      event.data = data;
      jEvent.launchCases(cases);
    }
  });