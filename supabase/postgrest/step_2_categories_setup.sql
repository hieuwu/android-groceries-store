WITH inserted_categories AS (
    INSERT INTO public.categories (name, image)
    VALUES
        ('Beverages', 'https://example.com/images/beverages.jpg'),
        ('Snacks', 'https://example.com/images/snacks.jpg'),
        ('Desserts', 'https://example.com/images/desserts.jpg'),
        ('Fruits', 'https://example.com/images/fruits.jpg'),
        ('Vegetables', 'https://example.com/images/vegetables.jpg'),
        ('Dairy', 'https://example.com/images/dairy.jpg'),
        ('Bakery', 'https://example.com/images/bakery.jpg'),
        ('Meat', 'https://example.com/images/meat.jpg'),
        ('Seafood', 'https://example.com/images/seafood.jpg'),
        ('Grains', 'https://example.com/images/grains.jpg')
    RETURNING id, name
)
SELECT id, name FROM inserted_categories;