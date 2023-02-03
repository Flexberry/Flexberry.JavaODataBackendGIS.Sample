CREATE TABLE public.favoritefeature
(
    userkey character varying(50) NOT NULL,
    primarykey uuid NOT NULL,
    objectlayerkey uuid NOT NULL,
    objectkey uuid NOT NULL,
    edittime timestamp(3) without time zone,
    editor character varying(255),
    creator character varying(255),
    createtime timestamp(3) without time zone,
    CONSTRAINT favoritefeature_pkey PRIMARY KEY (primarykey)
)
