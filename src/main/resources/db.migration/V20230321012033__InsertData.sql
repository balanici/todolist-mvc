insert into lists  (uuid, "name", description)
 values
('939276f3-bc9e-4522-8eb1-3e0ccfadbc98', 'List 1', 'Description list 1'),
('8d027fa7-9adf-439e-bceb-0f0b9d93d077', 'List 2', 'Description list 2');

insert into items (uuid, "name", description, list_entity_uuid)
 values
(gen_random_uuid(), 'Item 1', 'Description item 1', '939276f3-bc9e-4522-8eb1-3e0ccfadbc98'),
(gen_random_uuid(), 'Item 2', 'Description item 2', '8d027fa7-9adf-439e-bceb-0f0b9d93d077'),
(gen_random_uuid(), 'Item 3', 'Description item 3', '939276f3-bc9e-4522-8eb1-3e0ccfadbc98'),
(gen_random_uuid(), 'Item 4', 'Description item 4', '8d027fa7-9adf-439e-bceb-0f0b9d93d077'),
(gen_random_uuid(), 'Item 5', 'Description item 5', '939276f3-bc9e-4522-8eb1-3e0ccfadbc98'),
(gen_random_uuid(), 'Item 6', 'Description item 6', '8d027fa7-9adf-439e-bceb-0f0b9d93d077'),
(gen_random_uuid(), 'Item 7', 'Description item 7', '939276f3-bc9e-4522-8eb1-3e0ccfadbc98'),
(gen_random_uuid(), 'Item 8', 'Description item 8', '8d027fa7-9adf-439e-bceb-0f0b9d93d077'),
(gen_random_uuid(), 'Item 9', 'Description item 9', '939276f3-bc9e-4522-8eb1-3e0ccfadbc98');

