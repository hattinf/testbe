-- Adminer 4.8.1 PostgreSQL 12.18 (Debian 12.18-1.pgdg120+2) dump


CREATE TABLE "public"."component" (
    "id" bigint NOT NULL,
    "name" character varying(255),
    "page_id" bigint NOT NULL,
    CONSTRAINT "component_pkey" PRIMARY KEY ("id")
) WITH (oids = false);

INSERT INTO "component" ("id", "name", "page_id") VALUES
(454,	'LEO A',	506),
(455,	'ZENDAYA',	505),
(552,	'You get Spice HERE',	504),
(504,	'House of Athrethis',	502);

CREATE TABLE "public"."footer" (
    "id" bigint NOT NULL,
    "logo" character varying(255),
    "website_id" bigint NOT NULL,
    CONSTRAINT "footer_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "uk_7ya4xwwnrcyvtglp498yn7au5" UNIQUE ("website_id")
) WITH (oids = false);

INSERT INTO "footer" ("id", "logo", "website_id") VALUES
(1,	'dasdasd',	703);

CREATE TABLE "public"."footer_links" (
    "id" bigint NOT NULL,
    "name" character varying(255),
    "url" character varying(255),
    "footer_id" bigint NOT NULL,
    CONSTRAINT "footer_links_pkey" PRIMARY KEY ("id")
) WITH (oids = false);

INSERT INTO "footer_links" ("id", "name", "url", "footer_id") VALUES
(1,	'HOME',	'/',	1),
(2,	'ARAKIS',	'/arakis',	1);

CREATE TABLE "public"."navigation" (
    "id" bigint NOT NULL,
    "logo" character varying(255),
    "website_id" bigint NOT NULL,
    CONSTRAINT "navigation_pkey" PRIMARY KEY ("id"),
    CONSTRAINT "uk_12mxdcurnn7hsxu7o365cbx56" UNIQUE ("website_id")
) WITH (oids = false);

INSERT INTO "navigation" ("id", "logo", "website_id") VALUES
(1,	'asdasd',	703);

CREATE TABLE "public"."navigation_links" (
    "id" bigint NOT NULL,
    "name" character varying(255),
    "url" character varying(255),
    "navigation_id" bigint NOT NULL,
    CONSTRAINT "navigation_links_pkey" PRIMARY KEY ("id")
) WITH (oids = false);

INSERT INTO "navigation_links" ("id", "name", "url", "navigation_id") VALUES
(1,	'HOME',	'/',	1),
(2,	'ARAKIS',	'/arakis',	1);

CREATE TABLE "public"."pages" (
    "id" bigint NOT NULL,
    "slug" character varying(255),
    "sub_route" boolean,
    "title" character varying(255),
    "parent_page_id" bigint,
    "website_id" bigint NOT NULL,
    CONSTRAINT "pages_pkey" PRIMARY KEY ("id")
) WITH (oids = false);

INSERT INTO "pages" ("id", "slug", "sub_route", "title", "parent_page_id", "website_id") VALUES
(504,	'/arakis',	'f',	'Arakis',	NULL,	703),
(505,	'/fremen',	't',	'Fremen',	504,	703),
(506,	'/duke',	't',	'Duke',	504,	703),
(502,	'/',	'f',	'HOUSE',	NULL,	703);

CREATE TABLE "public"."websites" (
    "id" bigint NOT NULL,
    "name" character varying(255),
    CONSTRAINT "websites_pkey" PRIMARY KEY ("id")
) WITH (oids = false);

INSERT INTO "websites" ("id", "name") VALUES
(703,	'DUNE 2'),
(752,	'DUNE 3');

ALTER TABLE ONLY "public"."component" ADD CONSTRAINT "fk5a5av1j6gqyxt5e1cai87y3sh" FOREIGN KEY (page_id) REFERENCES pages(id) NOT DEFERRABLE;
ALTER TABLE ONLY "public"."footer" ADD CONSTRAINT "fk1ub2wtefd3cw5a7gpf08vm7m8" FOREIGN KEY (website_id) REFERENCES websites(id) NOT DEFERRABLE;
ALTER TABLE ONLY "public"."footer_links" ADD CONSTRAINT "fk1tw6o1oej8r4qekq5en498oue" FOREIGN KEY (footer_id) REFERENCES footer(id) NOT DEFERRABLE;
ALTER TABLE ONLY "public"."navigation" ADD CONSTRAINT "fk16axopkm7bcp3v6x1bloew2uk" FOREIGN KEY (website_id) REFERENCES websites(id) NOT DEFERRABLE;
ALTER TABLE ONLY "public"."navigation_links" ADD CONSTRAINT "fkmpe0a9u8px7ooynupt1lapx4s" FOREIGN KEY (navigation_id) REFERENCES navigation(id) NOT DEFERRABLE;
ALTER TABLE ONLY "public"."pages" ADD CONSTRAINT "fkklbg6piomofnvfp821jl6b5cd" FOREIGN KEY (parent_page_id) REFERENCES pages(id) NOT DEFERRABLE;
ALTER TABLE ONLY "public"."pages" ADD CONSTRAINT "fkmx8wk42rj7jno38akycq6f5vu" FOREIGN KEY (website_id) REFERENCES websites(id) NOT DEFERRABLE;

-- 2024-03-11 15:39:58.4856+00