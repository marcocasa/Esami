jQuery("#simulation")
  .on("click", ".s-03b38d2b-b877-4da5-bfa3-ca41c98702e7 .click", function(event, data) {
    var jEvent, jFirer, cases;
    if(data === undefined) { data = event; }
    jEvent = jimEvent(event);
    jFirer = jEvent.getEventFirer();
    if(jFirer.is("#s-star-1")) {
      cases = [
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-03b38d2b-b877-4da5-bfa3-ca41c98702e7 #s-star-1 > svg": {
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
                    "#s-03b38d2b-b877-4da5-bfa3-ca41c98702e7 #s-star-1_2 > svg": {
                      "attributes": {
                        "overlay": "#CCCCCC"
                      }
                    }
                  },{
                    "#s-03b38d2b-b877-4da5-bfa3-ca41c98702e7 #s-star-1_1 > svg": {
                      "attributes": {
                        "overlay": "#CCCCCC"
                      }
                    }
                  },{
                    "#s-03b38d2b-b877-4da5-bfa3-ca41c98702e7 #s-star-1_3 > svg": {
                      "attributes": {
                        "overlay": "#CCCCCC"
                      }
                    }
                  },{
                    "#s-03b38d2b-b877-4da5-bfa3-ca41c98702e7 #s-star-1_4 > svg": {
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
                    "#s-03b38d2b-b877-4da5-bfa3-ca41c98702e7 #s-star-1_1 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  },{
                    "#s-03b38d2b-b877-4da5-bfa3-ca41c98702e7 #s-star-1 > svg": {
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
                    "#s-03b38d2b-b877-4da5-bfa3-ca41c98702e7 #s-star-1_2 > svg": {
                      "attributes": {
                        "overlay": "#CCCCCC"
                      }
                    }
                  },{
                    "#s-03b38d2b-b877-4da5-bfa3-ca41c98702e7 #s-star-1_3 > svg": {
                      "attributes": {
                        "overlay": "#CCCCCC"
                      }
                    }
                  },{
                    "#s-03b38d2b-b877-4da5-bfa3-ca41c98702e7 #s-star-1_4 > svg": {
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
                    "#s-03b38d2b-b877-4da5-bfa3-ca41c98702e7 #s-star-1_2 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  },{
                    "#s-03b38d2b-b877-4da5-bfa3-ca41c98702e7 #s-star-1_1 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  },{
                    "#s-03b38d2b-b877-4da5-bfa3-ca41c98702e7 #s-star-1 > svg": {
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
                    "#s-03b38d2b-b877-4da5-bfa3-ca41c98702e7 #s-star-1_3 > svg": {
                      "attributes": {
                        "overlay": "#CCCCCC"
                      }
                    }
                  },{
                    "#s-03b38d2b-b877-4da5-bfa3-ca41c98702e7 #s-star-1_4 > svg": {
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
                    "#s-03b38d2b-b877-4da5-bfa3-ca41c98702e7 #s-star-1_2 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  },{
                    "#s-03b38d2b-b877-4da5-bfa3-ca41c98702e7 #s-star-1_1 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  },{
                    "#s-03b38d2b-b877-4da5-bfa3-ca41c98702e7 #s-star-1_3 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  },{
                    "#s-03b38d2b-b877-4da5-bfa3-ca41c98702e7 #s-star-1 > svg": {
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
                    "#s-03b38d2b-b877-4da5-bfa3-ca41c98702e7 #s-star-1_4 > svg": {
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
                    "#s-03b38d2b-b877-4da5-bfa3-ca41c98702e7 #s-star-1_2 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  },{
                    "#s-03b38d2b-b877-4da5-bfa3-ca41c98702e7 #s-star-1_1 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  },{
                    "#s-03b38d2b-b877-4da5-bfa3-ca41c98702e7 #s-star-1_4 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  },{
                    "#s-03b38d2b-b877-4da5-bfa3-ca41c98702e7 #s-star-1_3 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  },{
                    "#s-03b38d2b-b877-4da5-bfa3-ca41c98702e7 #s-star-1 > svg": {
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
    } else if(jFirer.is("#s-star-1_5")) {
      cases = [
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-03b38d2b-b877-4da5-bfa3-ca41c98702e7 #s-star-1_5 > svg": {
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
                    "#s-03b38d2b-b877-4da5-bfa3-ca41c98702e7 #s-star-1_7 > svg": {
                      "attributes": {
                        "overlay": "#CCCCCC"
                      }
                    }
                  },{
                    "#s-03b38d2b-b877-4da5-bfa3-ca41c98702e7 #s-star-1_6 > svg": {
                      "attributes": {
                        "overlay": "#CCCCCC"
                      }
                    }
                  },{
                    "#s-03b38d2b-b877-4da5-bfa3-ca41c98702e7 #s-star-1_8 > svg": {
                      "attributes": {
                        "overlay": "#CCCCCC"
                      }
                    }
                  },{
                    "#s-03b38d2b-b877-4da5-bfa3-ca41c98702e7 #s-star-1_9 > svg": {
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
                    "#s-03b38d2b-b877-4da5-bfa3-ca41c98702e7 #s-star-1_6 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  },{
                    "#s-03b38d2b-b877-4da5-bfa3-ca41c98702e7 #s-star-1_5 > svg": {
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
                    "#s-03b38d2b-b877-4da5-bfa3-ca41c98702e7 #s-star-1_7 > svg": {
                      "attributes": {
                        "overlay": "#CCCCCC"
                      }
                    }
                  },{
                    "#s-03b38d2b-b877-4da5-bfa3-ca41c98702e7 #s-star-1_8 > svg": {
                      "attributes": {
                        "overlay": "#CCCCCC"
                      }
                    }
                  },{
                    "#s-03b38d2b-b877-4da5-bfa3-ca41c98702e7 #s-star-1_9 > svg": {
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
                    "#s-03b38d2b-b877-4da5-bfa3-ca41c98702e7 #s-star-1_7 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  },{
                    "#s-03b38d2b-b877-4da5-bfa3-ca41c98702e7 #s-star-1_6 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  },{
                    "#s-03b38d2b-b877-4da5-bfa3-ca41c98702e7 #s-star-1_5 > svg": {
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
                    "#s-03b38d2b-b877-4da5-bfa3-ca41c98702e7 #s-star-1_8 > svg": {
                      "attributes": {
                        "overlay": "#CCCCCC"
                      }
                    }
                  },{
                    "#s-03b38d2b-b877-4da5-bfa3-ca41c98702e7 #s-star-1_9 > svg": {
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
                    "#s-03b38d2b-b877-4da5-bfa3-ca41c98702e7 #s-star-1_7 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  },{
                    "#s-03b38d2b-b877-4da5-bfa3-ca41c98702e7 #s-star-1_6 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  },{
                    "#s-03b38d2b-b877-4da5-bfa3-ca41c98702e7 #s-star-1_8 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  },{
                    "#s-03b38d2b-b877-4da5-bfa3-ca41c98702e7 #s-star-1_5 > svg": {
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
                    "#s-03b38d2b-b877-4da5-bfa3-ca41c98702e7 #s-star-1_9 > svg": {
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
                    "#s-03b38d2b-b877-4da5-bfa3-ca41c98702e7 #s-star-1_7 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  },{
                    "#s-03b38d2b-b877-4da5-bfa3-ca41c98702e7 #s-star-1_6 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  },{
                    "#s-03b38d2b-b877-4da5-bfa3-ca41c98702e7 #s-star-1_9 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  },{
                    "#s-03b38d2b-b877-4da5-bfa3-ca41c98702e7 #s-star-1_8 > svg": {
                      "attributes": {
                        "overlay": "#FACA51"
                      }
                    }
                  },{
                    "#s-03b38d2b-b877-4da5-bfa3-ca41c98702e7 #s-star-1_5 > svg": {
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
    } else if(jFirer.is("#s-Ellipse_2")) {
      cases = [
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimNavigation",
                  "parameter": {
                    "target": "screens/00275739-e229-4b9d-a259-87863ff27812",
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