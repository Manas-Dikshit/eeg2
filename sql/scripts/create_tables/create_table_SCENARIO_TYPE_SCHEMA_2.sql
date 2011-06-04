create table SCENARIO_TYPE_SCHEMA_2 (
  SCENARIO_ID number not nulL,
  SCENARIO_XML xmltype,
  CONSTRAINT SCENARIO_TYPE_SCHEMA_2_PK
    PRIMARY KEY(SCENARIO_ID),
  CONSTRAINT SCENARIO_TYPE_2_SCENARIO_FK1
    FOREIGN KEY(SCENARIO_ID)
    REFERENCES SCENARIO_TYPE_PARENT(SCENARIO_ID)
    ON DELETE CASCADE
  )
  xmltype SCENARIO_XML store as object relational
  xmlschema "p300.xsd"
  ELEMENT "scenario";