CREATE TABLE public.favoritefeatures
(
    userkey character varying(50) NOT NULL,
    primarykey uuid NOT NULL,
    objectlayerkey uuid NOT NULL,
    objectkey uuid NOT NULL,
    edittime timestamp(3) without time zone,
    editor character varying(255),
    creator character varying(255),
    createtime timestamp(3) without time zone,
    CONSTRAINT favoritefeatures_pkey PRIMARY KEY (primarykey)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.favoritefeatures
    OWNER to flexberryuser;