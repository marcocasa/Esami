jQuery("#simulation")
  .on("click", ".s-7f1f97e4-8193-4cc7-8462-3ce809af458c .click", function(event, data) {
    var jEvent, jFirer, cases;
    if(data === undefined) { data = event; }
    jEvent = jimEvent(event);
    jFirer = jEvent.getEventFirer();
    if(jFirer.is("#s-Ellipse_1")) {
      cases = [
        {
          "blocks": [
            {
              "condition": {
                "action": "jimEquals",
                "parameter": [ {
                  "datatype": "variable",
                  "element": "vestiti"
                },"celeste" ]
              },
              "actions": [
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-7f1f97e4-8193-4cc7-8462-3ce809af458c #s-Ellipse_1": {
                      "attributes": {
                        "background-color": "#3B5A9D",
                        "background-attachment": "scroll"
                      }
                    }
                  },{
                    "#s-7f1f97e4-8193-4cc7-8462-3ce809af458c #s-Ellipse_1": {
                      "attributes-ie": {
                        "-pie-background": "#3B5A9D",
                        "-pie-poll": "false"
                      }
                    }
                  } ],
                  "exectype": "serial",
                  "delay": 0
                },
                {
                  "action": "jimSetValue",
                  "parameter": {
                    "variable": [ "vestiti" ],
                    "value": "blu"
                  },
                  "exectype": "serial",
                  "delay": 0
                }
              ]
            },
            {
              "actions": [
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-7f1f97e4-8193-4cc7-8462-3ce809af458c #s-Ellipse_1": {
                      "attributes": {
                        "background-color": "#4FB2AA",
                        "background-attachment": "scroll"
                      }
                    }
                  },{
                    "#s-7f1f97e4-8193-4cc7-8462-3ce809af458c #s-Ellipse_1": {
                      "attributes-ie": {
                        "-pie-background": "#4FB2AA",
                        "-pie-poll": "false"
                      }
                    }
                  } ],
                  "exectype": "serial",
                  "delay": 0
                },
                {
                  "action": "jimSetValue",
                  "parameter": {
                    "variable": [ "vestiti" ],
                    "value": "celeste"
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
    } else if(jFirer.is("#s-Ellipse_2")) {
      cases = [
        {
          "blocks": [
            {
              "condition": {
                "action": "jimEquals",
                "parameter": [ {
                  "datatype": "variable",
                  "element": "cibo"
                },"celeste" ]
              },
              "actions": [
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-7f1f97e4-8193-4cc7-8462-3ce809af458c #s-Ellipse_2": {
                      "attributes": {
                        "background-color": "#3B5A9D",
                        "background-attachment": "scroll"
                      }
                    }
                  },{
                    "#s-7f1f97e4-8193-4cc7-8462-3ce809af458c #s-Ellipse_2": {
                      "attributes-ie": {
                        "-pie-background": "#3B5A9D",
                        "-pie-poll": "false"
                      }
                    }
                  } ],
                  "exectype": "serial",
                  "delay": 0
                },
                {
                  "action": "jimSetValue",
                  "parameter": {
                    "variable": [ "cibo" ],
                    "value": "blu"
                  },
                  "exectype": "serial",
                  "delay": 0
                }
              ]
            },
            {
              "actions": [
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-7f1f97e4-8193-4cc7-8462-3ce809af458c #s-Ellipse_2": {
                      "attributes": {
                        "background-color": "#4FB2AA",
                        "background-attachment": "scroll"
                      }
                    }
                  },{
                    "#s-7f1f97e4-8193-4cc7-8462-3ce809af458c #s-Ellipse_2": {
                      "attributes-ie": {
                        "-pie-background": "#4FB2AA",
                        "-pie-poll": "false"
                      }
                    }
                  } ],
                  "exectype": "serial",
                  "delay": 0
                },
                {
                  "action": "jimSetValue",
                  "parameter": {
                    "variable": [ "cibo" ],
                    "value": "celeste"
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
              "condition": {
                "action": "jimEquals",
                "parameter": [ {
                  "datatype": "variable",
                  "element": "prodottiFarmaceutici"
                },"celeste" ]
              },
              "actions": [
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-7f1f97e4-8193-4cc7-8462-3ce809af458c #s-Ellipse_3": {
                      "attributes": {
                        "background-color": "#3B5A9D",
                        "background-attachment": "scroll"
                      }
                    }
                  },{
                    "#s-7f1f97e4-8193-4cc7-8462-3ce809af458c #s-Ellipse_3": {
                      "attributes-ie": {
                        "-pie-background": "#3B5A9D",
                        "-pie-poll": "false"
                      }
                    }
                  } ],
                  "exectype": "serial",
                  "delay": 0
                },
                {
                  "action": "jimSetValue",
                  "parameter": {
                    "variable": [ "prodottiFarmaceutici" ],
                    "value": "blu"
                  },
                  "exectype": "serial",
                  "delay": 0
                }
              ]
            },
            {
              "actions": [
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-7f1f97e4-8193-4cc7-8462-3ce809af458c #s-Ellipse_3": {
                      "attributes": {
                        "background-color": "#4FB2AA",
                        "background-attachment": "scroll"
                      }
                    }
                  },{
                    "#s-7f1f97e4-8193-4cc7-8462-3ce809af458c #s-Ellipse_3": {
                      "attributes-ie": {
                        "-pie-background": "#4FB2AA",
                        "-pie-poll": "false"
                      }
                    }
                  } ],
                  "exectype": "serial",
                  "delay": 0
                },
                {
                  "action": "jimSetValue",
                  "parameter": {
                    "variable": [ "prodottiFarmaceutici" ],
                    "value": "celeste"
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
    } else if(jFirer.is("#s-Ellipse_4")) {
      cases = [
        {
          "blocks": [
            {
              "condition": {
                "action": "jimEquals",
                "parameter": [ {
                  "datatype": "variable",
                  "element": "detersivi"
                },"celeste" ]
              },
              "actions": [
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-7f1f97e4-8193-4cc7-8462-3ce809af458c #s-Ellipse_4": {
                      "attributes": {
                        "background-color": "#3B5A9D",
                        "background-attachment": "scroll"
                      }
                    }
                  },{
                    "#s-7f1f97e4-8193-4cc7-8462-3ce809af458c #s-Ellipse_4": {
                      "attributes-ie": {
                        "-pie-background": "#3B5A9D",
                        "-pie-poll": "false"
                      }
                    }
                  } ],
                  "exectype": "serial",
                  "delay": 0
                },
                {
                  "action": "jimSetValue",
                  "parameter": {
                    "variable": [ "detersivi" ],
                    "value": "blu"
                  },
                  "exectype": "serial",
                  "delay": 0
                }
              ]
            },
            {
              "actions": [
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-7f1f97e4-8193-4cc7-8462-3ce809af458c #s-Ellipse_4": {
                      "attributes": {
                        "background-color": "#4FB2AA",
                        "background-attachment": "scroll"
                      }
                    }
                  },{
                    "#s-7f1f97e4-8193-4cc7-8462-3ce809af458c #s-Ellipse_4": {
                      "attributes-ie": {
                        "-pie-background": "#4FB2AA",
                        "-pie-poll": "false"
                      }
                    }
                  } ],
                  "exectype": "serial",
                  "delay": 0
                },
                {
                  "action": "jimSetValue",
                  "parameter": {
                    "variable": [ "detersivi" ],
                    "value": "celeste"
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
    } else if(jFirer.is("#s-Ellipse_5")) {
      cases = [
        {
          "blocks": [
            {
              "condition": {
                "action": "jimEquals",
                "parameter": [ {
                  "datatype": "variable",
                  "element": "ideeRegalo"
                },"celeste" ]
              },
              "actions": [
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-7f1f97e4-8193-4cc7-8462-3ce809af458c #s-Ellipse_5": {
                      "attributes": {
                        "background-color": "#3B5A9D",
                        "background-attachment": "scroll"
                      }
                    }
                  },{
                    "#s-7f1f97e4-8193-4cc7-8462-3ce809af458c #s-Ellipse_5": {
                      "attributes-ie": {
                        "-pie-background": "#3B5A9D",
                        "-pie-poll": "false"
                      }
                    }
                  } ],
                  "exectype": "serial",
                  "delay": 0
                },
                {
                  "action": "jimSetValue",
                  "parameter": {
                    "variable": [ "ideeRegalo" ],
                    "value": "blu"
                  },
                  "exectype": "serial",
                  "delay": 0
                }
              ]
            },
            {
              "actions": [
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-7f1f97e4-8193-4cc7-8462-3ce809af458c #s-Ellipse_5": {
                      "attributes": {
                        "background-color": "#4FB2AA",
                        "background-attachment": "scroll"
                      }
                    }
                  },{
                    "#s-7f1f97e4-8193-4cc7-8462-3ce809af458c #s-Ellipse_5": {
                      "attributes-ie": {
                        "-pie-background": "#4FB2AA",
                        "-pie-poll": "false"
                      }
                    }
                  } ],
                  "exectype": "serial",
                  "delay": 0
                },
                {
                  "action": "jimSetValue",
                  "parameter": {
                    "variable": [ "ideeRegalo" ],
                    "value": "celeste"
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
    } else if(jFirer.is("#s-Ellipse_6")) {
      cases = [
        {
          "blocks": [
            {
              "condition": {
                "action": "jimEquals",
                "parameter": [ {
                  "datatype": "variable",
                  "element": "fastFood"
                },"celeste" ]
              },
              "actions": [
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-7f1f97e4-8193-4cc7-8462-3ce809af458c #s-Ellipse_6": {
                      "attributes": {
                        "background-color": "#3B5A9D",
                        "background-attachment": "scroll"
                      }
                    }
                  },{
                    "#s-7f1f97e4-8193-4cc7-8462-3ce809af458c #s-Ellipse_6": {
                      "attributes-ie": {
                        "-pie-background": "#3B5A9D",
                        "-pie-poll": "false"
                      }
                    }
                  } ],
                  "exectype": "serial",
                  "delay": 0
                },
                {
                  "action": "jimSetValue",
                  "parameter": {
                    "variable": [ "fastFood" ],
                    "value": "blu"
                  },
                  "exectype": "serial",
                  "delay": 0
                }
              ]
            },
            {
              "actions": [
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-7f1f97e4-8193-4cc7-8462-3ce809af458c #s-Ellipse_6": {
                      "attributes": {
                        "background-color": "#4FB2AA",
                        "background-attachment": "scroll"
                      }
                    }
                  },{
                    "#s-7f1f97e4-8193-4cc7-8462-3ce809af458c #s-Ellipse_6": {
                      "attributes-ie": {
                        "-pie-background": "#4FB2AA",
                        "-pie-poll": "false"
                      }
                    }
                  } ],
                  "exectype": "serial",
                  "delay": 0
                },
                {
                  "action": "jimSetValue",
                  "parameter": {
                    "variable": [ "fastFood" ],
                    "value": "celeste"
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
    } else if(jFirer.is("#s-Ellipse_7")) {
      cases = [
        {
          "blocks": [
            {
              "condition": {
                "action": "jimEquals",
                "parameter": [ {
                  "datatype": "variable",
                  "element": "ricambiAuto"
                },"celeste" ]
              },
              "actions": [
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-7f1f97e4-8193-4cc7-8462-3ce809af458c #s-Ellipse_7": {
                      "attributes": {
                        "background-color": "#3B5A9D",
                        "background-attachment": "scroll"
                      }
                    }
                  },{
                    "#s-7f1f97e4-8193-4cc7-8462-3ce809af458c #s-Ellipse_7": {
                      "attributes-ie": {
                        "-pie-background": "#3B5A9D",
                        "-pie-poll": "false"
                      }
                    }
                  } ],
                  "exectype": "serial",
                  "delay": 0
                },
                {
                  "action": "jimSetValue",
                  "parameter": {
                    "variable": [ "ricambiAuto" ],
                    "value": "blu"
                  },
                  "exectype": "serial",
                  "delay": 0
                }
              ]
            },
            {
              "actions": [
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-7f1f97e4-8193-4cc7-8462-3ce809af458c #s-Ellipse_7": {
                      "attributes": {
                        "background-color": "#4FB2AA",
                        "background-attachment": "scroll"
                      }
                    }
                  },{
                    "#s-7f1f97e4-8193-4cc7-8462-3ce809af458c #s-Ellipse_7": {
                      "attributes-ie": {
                        "-pie-background": "#4FB2AA",
                        "-pie-poll": "false"
                      }
                    }
                  } ],
                  "exectype": "serial",
                  "delay": 0
                },
                {
                  "action": "jimSetValue",
                  "parameter": {
                    "variable": [ "ricambiAuto" ],
                    "value": "celeste"
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
    } else if(jFirer.is("#s-Ellipse_8")) {
      cases = [
        {
          "blocks": [
            {
              "condition": {
                "action": "jimEquals",
                "parameter": [ {
                  "datatype": "variable",
                  "element": "outlet"
                },"celeste" ]
              },
              "actions": [
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-7f1f97e4-8193-4cc7-8462-3ce809af458c #s-Ellipse_8": {
                      "attributes": {
                        "background-color": "#3B5A9D",
                        "background-attachment": "scroll"
                      }
                    }
                  },{
                    "#s-7f1f97e4-8193-4cc7-8462-3ce809af458c #s-Ellipse_8": {
                      "attributes-ie": {
                        "-pie-background": "#3B5A9D",
                        "-pie-poll": "false"
                      }
                    }
                  } ],
                  "exectype": "serial",
                  "delay": 0
                },
                {
                  "action": "jimSetValue",
                  "parameter": {
                    "variable": [ "outlet" ],
                    "value": "blu"
                  },
                  "exectype": "serial",
                  "delay": 0
                }
              ]
            },
            {
              "actions": [
                {
                  "action": "jimChangeStyle",
                  "parameter": [ {
                    "#s-7f1f97e4-8193-4cc7-8462-3ce809af458c #s-Ellipse_8": {
                      "attributes": {
                        "background-color": "#4FB2AA",
                        "background-attachment": "scroll"
                      }
                    }
                  },{
                    "#s-7f1f97e4-8193-4cc7-8462-3ce809af458c #s-Ellipse_8": {
                      "attributes-ie": {
                        "-pie-background": "#4FB2AA",
                        "-pie-poll": "false"
                      }
                    }
                  } ],
                  "exectype": "serial",
                  "delay": 0
                },
                {
                  "action": "jimSetValue",
                  "parameter": {
                    "variable": [ "outlet" ],
                    "value": "celeste"
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
    } else if(jFirer.is("#s-Rectangle_1")) {
      cases = [
        {
          "blocks": [
            {
              "actions": [
                {
                  "action": "jimNavigation",
                  "parameter": {
                    "target": "screens/0f0f1df9-517c-4f88-a099-d643fa62a62f",
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