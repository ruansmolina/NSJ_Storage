
CREATE TABLE IF NOT EXISTS public.products
(
    prod_id BIGSERIAL  NOT NULL,
    prod_name character varying(50) NOT NULL,
    prod_price numeric(12, 2) NOT NULL,
    "prod_SKU" character varying(15),
    PRIMARY KEY (prod_id)
);

CREATE TABLE IF NOT EXISTS public.storage
(
    stor_id BIGSERIAL  NOT NULL,
    stor_quantity bigint NOT NULL,
    "stor_lastupdate" timestamp without time zone NOT NULL,
    stor_prod_id bigint,
    PRIMARY KEY (stor_id)
);

CREATE TABLE IF NOT EXISTS public.sales
(
    sales_id BIGSERIAL  NOT NULL,
    sales_date timestamp without time zone NOT NULL,
    sales_total numeric(10, 2),
    PRIMARY KEY (sales_id)
);

CREATE TABLE IF NOT EXISTS public.sales_products
(
    sp_id BIGSERIAL  NOT NULL,
    sp_sales_id bigint NOT NULL,
    sp_prod_id bigint NOT NULL,
    sp_quantity bigint NOT NULL,
    sp_unitprice numeric(10, 2) NOT NULL,
    sp_subtotal numeric(10, 2) NOT NULL,
    PRIMARY KEY (sp_id)
);

ALTER TABLE IF EXISTS public.storage
    ADD FOREIGN KEY (stor_prod_id)
    REFERENCES public.products (prod_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.sales_products
    ADD FOREIGN KEY (sp_sales_id)
    REFERENCES public.sales (sales_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.sales_products
    ADD FOREIGN KEY (sp_prod_id)
    REFERENCES public.products (prod_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

