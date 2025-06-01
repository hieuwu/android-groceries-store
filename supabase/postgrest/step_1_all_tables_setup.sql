-- Drop tables in reverse dependency order to avoid foreign key conflicts
DROP TABLE IF EXISTS public.line_items;
DROP TABLE IF EXISTS public.meal_plans;
DROP TABLE IF EXISTS public.orders;
DROP TABLE IF EXISTS public.products;
DROP TABLE IF EXISTS public.users;
DROP TABLE IF EXISTS public.categories;

-- Create tables in dependency order
CREATE TABLE public.categories (
  image text,
  name text NOT NULL,
  id uuid NOT NULL DEFAULT gen_random_uuid() UNIQUE,
  CONSTRAINT categories_pkey PRIMARY KEY (id)
);

CREATE TABLE public.users (
  id uuid NOT NULL DEFAULT gen_random_uuid(),
  name text NOT NULL,
  email text NOT NULL UNIQUE,
  phone text,
  isordercreatednotienabled boolean DEFAULT false,
  ispromotionnotienabled boolean DEFAULT false,
  isdatarefreshednotienabled boolean DEFAULT false,
  address text,
  created_at timestamp with time zone DEFAULT now(),
  CONSTRAINT users_pkey PRIMARY KEY (id)
);

CREATE TABLE public.orders (
  orderid uuid NOT NULL DEFAULT gen_random_uuid(),
  status text NOT NULL,
  address text NOT NULL,
  created_at timestamp with time zone DEFAULT now(),
  total real NOT NULL,
  CONSTRAINT orders_pkey PRIMARY KEY (orderid)
);

CREATE TABLE public.products (
  id uuid NOT NULL DEFAULT gen_random_uuid(),
  name text NOT NULL,
  description text,
  price real NOT NULL,
  image text,
  nutrition text,
  category uuid,
  CONSTRAINT products_pkey PRIMARY KEY (id),
  CONSTRAINT products_category_fkey FOREIGN KEY (category) REFERENCES public.categories(id) ON DELETE RESTRICT
);

CREATE TABLE public.meal_plans (
  id uuid NOT NULL DEFAULT gen_random_uuid(),
  created_at timestamp with time zone DEFAULT now(),
  week_day text NOT NULL,
  name text NOT NULL,
  ingredients text,
  creator uuid NOT NULL,
  meal_type text NOT NULL,
  image text,
  CONSTRAINT meal_plans_pkey PRIMARY KEY (id),
  CONSTRAINT meal_plans_creator_fkey FOREIGN KEY (creator) REFERENCES public.users(id) ON DELETE CASCADE
);

CREATE TABLE public.line_items (
  id bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
  productid text NOT NULL,
  orderid uuid NOT NULL,
  quantity bigint NOT NULL,
  subtotal double precision NOT NULL,
  lineitemid bigint NOT NULL UNIQUE,
  CONSTRAINT line_items_pkey PRIMARY KEY (id),
  CONSTRAINT line_items_orderid_fkey FOREIGN KEY (orderid) REFERENCES public.orders(orderid) ON DELETE CASCADE
);