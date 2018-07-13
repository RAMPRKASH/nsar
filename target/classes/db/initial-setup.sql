CREATE TABLE network_type
(
  network_id serial NOT NULL,
  network_type character varying(32),
  created_at timestamp without time zone DEFAULT now(),
  created_by character varying(128),
  updated_at timestamp without time zone DEFAULT now(),
  updated_by character varying(128),
  CONSTRAINT network_type_pkey PRIMARY KEY (id)
)

CREATE TABLE hardware_type
(
  hardware_id serial NOT NULL,
  hardware_type character varying(32),
  created_at timestamp without time zone DEFAULT now(),
  created_by character varying(128),
  updated_at timestamp without time zone DEFAULT now(),
  updated_by character varying(128),
  CONSTRAINT network_type_pkey PRIMARY KEY (id)
)


CREATE TABLE bsc_details
(
  bsc_id serial NOT NULL,
  bsc_name character varying(64),
  region character varying(64),
  network_type_id integer REFERENCES network_type (network_id),
  hardware_type_id integer REFERENCES hardware_type (hardware_id),
  created_at timestamp without time zone DEFAULT now(),
  created_by character varying(128),
  updated_at timestamp without time zone DEFAULT now(),
  updated_by character varying(128),
  CONSTRAINT bsc_details_pkey PRIMARY KEY (id)
)

CREATE TABLE bsc_cell_details
(
  cell_id serial NOT NULL,
  cell_name character varying(64),
  bsc_id integer REFERENCES bsc_details (bsc_id),
  created_at timestamp without time zone DEFAULT now(),
  created_by character varying(128),
  updated_at timestamp without time zone DEFAULT now(),
  updated_by character varying(128),
  CONSTRAINT bsc_cell_details_pkey PRIMARY KEY (id)
)
	
CREATE TABLE rnc_details
(
  rnc_id serial NOT NULL,
  rnc_name character varying(64),
  region character varying(64),
  network_type_id integer REFERENCES network_type (network_id),
  hardware_type_id integer REFERENCES hardware_type (hardware_id),
  created_at timestamp without time zone DEFAULT now(),
  created_by character varying(128),
  updated_at timestamp without time zone DEFAULT now(),
  updated_by character varying(128),
  CONSTRAINT bsc_details_pkey PRIMARY KEY (id)
)

CREATE TABLE rnc_cell_details
(
  cell_id serial NOT NULL,
  cell_name character varying(64),
  rnc_id integer REFERENCES bsc_details (rnc_id),
  created_at timestamp without time zone DEFAULT now(),
  created_by character varying(128),
  updated_at timestamp without time zone DEFAULT now(),
  updated_by character varying(128),
  CONSTRAINT bsc_cell_details_pkey PRIMARY KEY (id)
)