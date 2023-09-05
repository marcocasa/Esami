jQuery("#simulation")
  .on("click", ".s-bd98c769-34e2-4e50-abdf-1150c88473b2 .click", function(event, data) {
    var jEvent, jFirer, cases;
    if(data === undefined) { data = event; }
    jEvent = jimEvent(event);
    jFirer = jEvent.getEventFirer();
    if(jFirer.is("#s-star-1_5")) {
      cases = [
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_5 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  } ],
                  "exectype": "serial",
                  "delay": 0
                },
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_7 > svg": {
                      "attributes": {
                        "overlay": "#CCCCCC"
                      }
                    }
                  },{
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_6 > svg": {
                      "attributes": {
                        "overlay": "#CCCCCC"
                      }
                    }
                  },{
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_8 > svg": {
                      "attributes": {
                        "overlay": "#CCCCCC"
                      }
                    }
                  },{
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_9 > svg": {
                      "attributes": {
                        "overlay": "#CCCCCC"
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
    } else if(jFirer.is("#s-star-1_6")) {
      cases = [
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_6 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  },{
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_5 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  } ],
                  "exectype": "serial",
                  "delay": 0
                },
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_7 > svg": {
                      "attributes": {
                        "overlay": "#CCCCCC"
                      }
                    }
                  },{
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_8 > svg": {
                      "attributes": {
                        "overlay": "#CCCCCC"
                      }
                    }
                  },{
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_9 > svg": {
                      "attributes": {
                        "overlay": "#CCCCCC"
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
    } else if(jFirer.is("#s-star-1_7")) {
      cases = [
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_7 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  },{
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_6 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  },{
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_5 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  } ],
                  "exectype": "serial",
                  "delay": 0
                },
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_8 > svg": {
                      "attributes": {
                        "overlay": "#CCCCCC"
                      }
                    }
                  },{
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_9 > svg": {
                      "attributes": {
                        "overlay": "#CCCCCC"
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
    } else if(jFirer.is("#s-star-1_8")) {
      cases = [
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_7 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  },{
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_6 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  },{
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_8 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  },{
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_5 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  } ],
                  "exectype": "serial",
                  "delay": 0
                },
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_9 > svg": {
                      "attributes": {
                        "overlay": "#CCCCCC"
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
    } else if(jFirer.is("#s-star-1_9")) {
      cases = [
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_7 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  },{
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_6 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  },{
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_9 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  },{
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_8 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  },{
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_5 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
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
    } else if(jFirer.is("#s-star-1")) {
      cases = [
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  } ],
                  "exectype": "serial",
                  "delay": 0
                },
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_2 > svg": {
                      "attributes": {
                        "overlay": "#CCCCCC"
                      }
                    }
                  },{
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_1 > svg": {
                      "attributes": {
                        "overlay": "#CCCCCC"
                      }
                    }
                  },{
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_3 > svg": {
                      "attributes": {
                        "overlay": "#CCCCCC"
                      }
                    }
                  },{
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_4 > svg": {
                      "attributes": {
                        "overlay": "#CCCCCC"
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
    } else if(jFirer.is("#s-star-1_1")) {
      cases = [
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_1 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  },{
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  } ],
                  "exectype": "serial",
                  "delay": 0
                },
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_2 > svg": {
                      "attributes": {
                        "overlay": "#CCCCCC"
                      }
                    }
                  },{
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_3 > svg": {
                      "attributes": {
                        "overlay": "#CCCCCC"
                      }
                    }
                  },{
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_4 > svg": {
                      "attributes": {
                        "overlay": "#CCCCCC"
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
    } else if(jFirer.is("#s-star-1_2")) {
      cases = [
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_2 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  },{
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_1 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  },{
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  } ],
                  "exectype": "serial",
                  "delay": 0
                },
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_3 > svg": {
                      "attributes": {
                        "overlay": "#CCCCCC"
                      }
                    }
                  },{
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_4 > svg": {
                      "attributes": {
                        "overlay": "#CCCCCC"
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
    } else if(jFirer.is("#s-star-1_3")) {
      cases = [
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_2 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  },{
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_1 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  },{
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_3 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  },{
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  } ],
                  "exectype": "serial",
                  "delay": 0
                },
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_4 > svg": {
                      "attributes": {
                        "overlay": "#CCCCCC"
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
    } else if(jFirer.is("#s-star-1_4")) {
      cases = [
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_2 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  },{
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_1 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  },{
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_4 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  },{
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_3 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  },{
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
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
    } else if(jFirer.is("#s-flat_Button_lightTheme")) {
      cases = [
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-flat_Button_lightTheme > .backgroundLayer": {
                      "attributes": {
                        "background-color": "#D6D6D6"
                      }
                    }
                  },{
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-flat_Button_lightTheme": {
                      "attributes-ie": {
                        "-pie-background": "#D6D6D6",
                        "-pie-poll": "false"
                      }
                    }
                  } ],
                  "exectype": "serial",
                  "delay": 0
                },
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-flat_Button_lightTheme > .backgroundLayer": {
                      "attributes": {
                        "background-color": "transparent"
                      }
                    }
                  },{
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-flat_Button_lightTheme": {
                      "attributes-ie": {
                        "-pie-background": "transparent",
                        "-pie-poll": "false"
                      }
                    }
                  } ],
                  "exectype": "timed",
                  "delay": 200
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
                  "action": "jimNavigation",
                  "parameter": {
                    "target": "screens/03b38d2b-b877-4da5-bfa3-ca41c98702e7"
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
    } else if(jFirer.is("#s-star-1_10")) {
      cases = [
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_10 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  } ],
                  "exectype": "serial",
                  "delay": 0
                },
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_12 > svg": {
                      "attributes": {
                        "overlay": "#CCCCCC"
                      }
                    }
                  },{
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_11 > svg": {
                      "attributes": {
                        "overlay": "#CCCCCC"
                      }
                    }
                  },{
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_13 > svg": {
                      "attributes": {
                        "overlay": "#CCCCCC"
                      }
                    }
                  },{
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_14 > svg": {
                      "attributes": {
                        "overlay": "#CCCCCC"
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
    } else if(jFirer.is("#s-star-1_11")) {
      cases = [
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_11 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  },{
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_10 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  } ],
                  "exectype": "serial",
                  "delay": 0
                },
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_12 > svg": {
                      "attributes": {
                        "overlay": "#CCCCCC"
                      }
                    }
                  },{
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_13 > svg": {
                      "attributes": {
                        "overlay": "#CCCCCC"
                      }
                    }
                  },{
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_14 > svg": {
                      "attributes": {
                        "overlay": "#CCCCCC"
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
    } else if(jFirer.is("#s-star-1_12")) {
      cases = [
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_12 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  },{
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_11 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  },{
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_10 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  } ],
                  "exectype": "serial",
                  "delay": 0
                },
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_13 > svg": {
                      "attributes": {
                        "overlay": "#CCCCCC"
                      }
                    }
                  },{
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_14 > svg": {
                      "attributes": {
                        "overlay": "#CCCCCC"
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
    } else if(jFirer.is("#s-star-1_13")) {
      cases = [
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_12 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  },{
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_11 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  },{
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_13 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  },{
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_10 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  } ],
                  "exectype": "serial",
                  "delay": 0
                },
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_14 > svg": {
                      "attributes": {
                        "overlay": "#CCCCCC"
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
    } else if(jFirer.is("#s-star-1_14")) {
      cases = [
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_12 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  },{
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_11 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  },{
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_14 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  },{
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_13 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  },{
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-star-1_10 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
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
    } else if(jFirer.is("#s-Panel_2")) {
      cases = [
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimNavigation",
                  "parameter": {
                    "target": "screens/8e5abb11-4cac-4de6-9d5d-fd756e3642e7",
                    "transition": {
                      "type": "slideleft",
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
    } else if(jFirer.is("#s-flat_Button_lightTheme_1")) {
      cases = [
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimNavigation",
                  "parameter": {
                    "target": "screens/8e5abb11-4cac-4de6-9d5d-fd756e3642e7",
                    "transition": {
                      "type": "slideleft",
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
    } else if(jFirer.is("#s-Image_81")) {
      cases = [
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimNavigation",
                  "parameter": {
                    "target": "screens/8e5abb11-4cac-4de6-9d5d-fd756e3642e7"
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
    } else if(jFirer.is("#s-Image_28")) {
      cases = [
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimShow",
                  "parameter": {
                    "target": [ "#s-Ellipse_2" ]
                  },
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
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-Image_28 > svg": {
                      "attributes": {
                        "overlay": "#D70000"
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
    } else if(jFirer.is("#s-Ellipse_2")) {
      cases = [
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimHide",
                  "parameter": {
                    "target": [ "#s-Ellipse_2" ]
                  },
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
                    "#s-bd98c769-34e2-4e50-abdf-1150c88473b2 #s-Image_28 > svg": {
                      "attributes": {
                        "overlay": "#D70000"
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
    } else if(jFirer.is("#s-Button_1")) {
      cases = [
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimNavigation",
                  "parameter": {
                    "target": "screens/42caed59-35ad-42f3-8d1f-dedc05c1384f",
                    "transition": {
                      "type": "slideleft",
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
    } else if(jFirer.is("#s-Rectangle_4")) {
      cases = [
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimShow",
                  "parameter": {
                    "target": [ "#s-Message-light","#s-Rectangle_7" ],
                    "effect": {
                      "type": "puff",
                      "easing": "linear",
                      "duration": 500
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
    } else if(jFirer.is("#s-Rectangle_3")) {
      cases = [
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimNavigation",
                  "parameter": {
                    "target": "screens/81981096-7ab7-4545-a766-c24abce8ef60",
                    "transition": {
                      "type": "slideleft",
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
    } else if(jFirer.is("#s-Ellipse_3")) {
      cases = [
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimNavigation",
                  "parameter": {
                    "target": "screens/00275739-e229-4b9d-a259-87863ff27812"
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
    } else if(jFirer.is("#s-Button_9")) {
      cases = [
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimNavigation",
                  "parameter": {
                    "target": "screens/b9c06432-ed96-4458-a48b-79865d62f6f5",
                    "transition": {
                      "type": "slideleft",
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
    } else if(jFirer.is("#s-Button_10")) {
      cases = [
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimNavigation",
                  "parameter": {
                    "target": "screens/b9c06432-ed96-4458-a48b-79865d62f6f5",
                    "transition": {
                      "type": "slideleft",
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
    } else if(jFirer.is("#s-Notification")) {
      cases = [
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimNavigation",
                  "parameter": {
                    "target": "screens/67270b71-c077-4833-882f-78201efd3514",
                    "transition": {
                      "type": "slideleft",
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
    } else if(jFirer.is("#s-Notification-Number")) {
      cases = [
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimNavigation",
                  "parameter": {
                    "target": "screens/67270b71-c077-4833-882f-78201efd3514"
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