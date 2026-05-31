INSERT INTO users (id, username, email, password, role, profile_picture_url, created_at)
VALUES (1, 'reza_reynaldi', 'reza@mail.com', 'hashed_password_1', 'HOME_COOK', 'http://example.com/reza.jpg', NOW());
INSERT INTO users (id, username, email, password, role, profile_picture_url, created_at)
VALUES (2, 'chef_arnold', 'arnold@smarterrecipe.com', 'hashed_password_2', 'RECIPE_CREATOR', 'http://example.com/arnold.jpg', NOW());
INSERT INTO users (id, username, email, password, role, profile_picture_url, created_at)
VALUES (3, 'healthy_clara', 'clara@mail.com', 'hashed_password_3', 'HOME_COOK', 'http://example.com/clara.jpg', NOW());
INSERT INTO users (id, username, email, password, role, profile_picture_url, created_at)
VALUES (4, 'admin_smarter', 'admin@smarterrecipe.com', 'hashed_password_4', 'RECIPE_CREATOR', 'http://example.com/admin.jpg', NOW());
INSERT INTO users (id, username, email, password, role, profile_picture_url, created_at)
VALUES (5, 'chef_juna', 'juna@smarterrecipe.com', 'hashed_password_5', 'RECIPE_CREATOR', 'http://example.com/juna.jpg', NOW());
INSERT INTO users (id, username, email, password, role, profile_picture_url, created_at)
VALUES (6, 'budi_hartono', 'budih@mail.com', 'hashed_password_6', 'HOME_COOK', 'http://example.com/budih.jpg', NOW());
INSERT INTO users (id, username, email, password, role, profile_picture_url, created_at)
VALUES (7, 'fit_sari', 'sari_fit@mail.com', 'hashed_password_7', 'HOME_COOK', 'http://example.com/sari.jpg', NOW());
INSERT INTO users (id, username, email, password, role, profile_picture_url, created_at)
VALUES (8, 'keto_bambang', 'bambangketo@mail.com', 'hashed_password_8', 'HOME_COOK', 'http://example.com/bambang.jpg', NOW());

INSERT INTO ingredients (id, name) VALUES (1, 'Gula Pasir');
INSERT INTO ingredients (id, name) VALUES (2, 'Garam Dapur');
INSERT INTO ingredients (id, name) VALUES (3, 'Bawang Putih');
INSERT INTO ingredients (id, name) VALUES (4, 'Kecap Manis');
INSERT INTO ingredients (id, name) VALUES (5, 'Minyak Goreng Kelapa');
INSERT INTO ingredients (id, name) VALUES (6, 'Merica Bubuk');
INSERT INTO ingredients (id, name) VALUES (7, 'Penyedap Rasa Jamur');
INSERT INTO ingredients (id, name) VALUES (8, 'Daging Babi');
INSERT INTO ingredients (id, name) VALUES (9, 'Daging Ayam');
INSERT INTO ingredients (id, name) VALUES (10, 'Susu Sapi');
INSERT INTO ingredients (id, name) VALUES (11, 'Susu Almond');
INSERT INTO ingredients (id, name) VALUES (12, 'Bawang Bombai');
INSERT INTO ingredients (id, name) VALUES (13, 'Wortel');
INSERT INTO ingredients (id, name) VALUES (14, 'Brokoli');
INSERT INTO ingredients (id, name) VALUES (15, 'Tahu Putih');
INSERT INTO ingredients (id, name) VALUES (16, 'Tempe Pemula');
INSERT INTO ingredients (id, name) VALUES (17, 'Minyak Zaitun');
INSERT INTO ingredients (id, name) VALUES (18, 'Cabai Rawit');
INSERT INTO ingredients (id, name) VALUES (19, 'Daging Sapi');
INSERT INTO ingredients (id, name) VALUES (20, 'Mentega Dairy');
INSERT INTO ingredients (id, name) VALUES (21, 'Daging Sapi Sirloin');
INSERT INTO ingredients (id, name) VALUES (22, 'Beras Merah');
INSERT INTO ingredients (id, name) VALUES (23, 'Kecap Asin');
INSERT INTO ingredients (id, name) VALUES (24, 'Minyak Wijen');
INSERT INTO ingredients (id, name) VALUES (25, 'Lada Hitam');
INSERT INTO ingredients (id, name) VALUES (26, 'Tepung Terigu');
INSERT INTO ingredients (id, name) VALUES (27, 'Tepung Tapioka');
INSERT INTO ingredients (id, name) VALUES (28, 'Bayam');
INSERT INTO ingredients (id, name) VALUES (29, 'Jamur Kancing');
INSERT INTO ingredients (id, name) VALUES (30, 'Susu Kedelai');
INSERT INTO ingredients (id, name) VALUES (31, 'Jagung Manis');
INSERT INTO ingredients (id, name) VALUES (32, 'Udang');
INSERT INTO ingredients (id, name) VALUES (33, 'Jeruk Nipis');
INSERT INTO ingredients (id, name) VALUES (34, 'Daun Bawang');
INSERT INTO ingredients (id, name) VALUES (35, 'Jahe');

INSERT INTO pantries (id, name, description) VALUES (1, 'Wajan Anti Lengket', 'Wajan teflon diameter 24cm');
INSERT INTO pantries (id, name, description) VALUES (2, 'Spatula Kayu', 'Spatula bahan kayu mahoni anti gores');
INSERT INTO pantries (id, name, description) VALUES (3, 'Blender', 'Blender bumbu dan buah kecepatan tinggi');
INSERT INTO pantries (id, name, description) VALUES (4, 'Oven Listrik', 'Oven pemanggang kue dan daging');
INSERT INTO pantries (id, name, description) VALUES (5, 'Pisau Dapur', 'Pisau stainless steel tajam');
INSERT INTO pantries (id, name, description) VALUES (6, 'Panci Sup', 'Panci ukuran sedang untuk merebus');
INSERT INTO pantries (id, name, description) VALUES (7, 'Air Fryer', 'Air fryer penggoreng tanpa minyak');
INSERT INTO pantries (id, name, description) VALUES (8, 'Panggangan Barbeque', 'Alat pemanggang daging indoor');
INSERT INTO pantries (id, name, description) VALUES (9, 'Talenan Kayu', 'Alat alas memotong bahan masakan');
INSERT INTO pantries (id, name, description) VALUES (10, 'Panci Kukus', 'Panci double layer untuk mengukus');

INSERT INTO user_pantries (id, user_id, pantry_id) VALUES (1, 1, 1);
INSERT INTO user_pantries (id, user_id, pantry_id) VALUES (2, 1, 2);
INSERT INTO user_pantries (id, user_id, pantry_id) VALUES (3, 3, 3);
INSERT INTO user_pantries (id, user_id, pantry_id) VALUES (4, 3, 5);
INSERT INTO user_pantries (id, user_id, pantry_id) VALUES (5, 3, 6);
INSERT INTO user_pantries (id, user_id, pantry_id) VALUES (6, 6, 7);
INSERT INTO user_pantries (id, user_id, pantry_id) VALUES (7, 6, 9);
INSERT INTO user_pantries (id, user_id, pantry_id) VALUES (8, 7, 9);
INSERT INTO user_pantries (id, user_id, pantry_id) VALUES (9, 7, 10);
INSERT INTO user_pantries (id, user_id, pantry_id) VALUES (10, 8, 8);
INSERT INTO user_pantries (id, user_id, pantry_id) VALUES (11, 8, 9);

INSERT INTO dietary_tags (id, name, description) VALUES (1, 'Halal', 'Makanan yang diizinkan dikonsumsi menurut syariat Islam');
INSERT INTO dietary_tags (id, name, description) VALUES (2, 'Vegan', 'Hanya mengonsumsi makanan nabati, tanpa produk hewani atau turunannya');
INSERT INTO dietary_tags (id, name, description) VALUES (3, 'Lactose Free', 'Aman bagi penderita intoleransi laktosa, bebas susu sapi');
INSERT INTO dietary_tags (id, name, description) VALUES (4, 'Keto', 'Diet rendah karbohidrat dan tinggi lemak');
INSERT INTO dietary_tags (id, name, description) VALUES (5, 'Seafood Free', 'Alergi makanan laut, tidak mengandung udang, ikan, kepiting');

INSERT INTO dietary_ingredient_restrictions (id, dietary_tag_id, ingredient_id) VALUES (1, 1, 8);
INSERT INTO dietary_ingredient_restrictions (id, dietary_tag_id, ingredient_id) VALUES (2, 2, 8);
INSERT INTO dietary_ingredient_restrictions (id, dietary_tag_id, ingredient_id) VALUES (3, 2, 9);
INSERT INTO dietary_ingredient_restrictions (id, dietary_tag_id, ingredient_id) VALUES (4, 2, 10);
INSERT INTO dietary_ingredient_restrictions (id, dietary_tag_id, ingredient_id) VALUES (5, 2, 2);
INSERT INTO dietary_ingredient_restrictions (id, dietary_tag_id, ingredient_id) VALUES (6, 2, 19);
INSERT INTO dietary_ingredient_restrictions (id, dietary_tag_id, ingredient_id) VALUES (7, 2, 20);
INSERT INTO dietary_ingredient_restrictions (id, dietary_tag_id, ingredient_id) VALUES (8, 3, 10);
INSERT INTO dietary_ingredient_restrictions (id, dietary_tag_id, ingredient_id) VALUES (9, 3, 20);
INSERT INTO dietary_ingredient_restrictions (id, dietary_tag_id, ingredient_id) VALUES (10, 4, 1);
INSERT INTO dietary_ingredient_restrictions (id, dietary_tag_id, ingredient_id) VALUES (11, 4, 5);
INSERT INTO dietary_ingredient_restrictions (id, dietary_tag_id, ingredient_id) VALUES (12, 4, 22);
INSERT INTO dietary_ingredient_restrictions (id, dietary_tag_id, ingredient_id) VALUES (13, 4, 26);
INSERT INTO dietary_ingredient_restrictions (id, dietary_tag_id, ingredient_id) VALUES (14, 4, 27);
INSERT INTO dietary_ingredient_restrictions (id, dietary_tag_id, ingredient_id) VALUES (15, 4, 31);
INSERT INTO dietary_ingredient_restrictions (id, dietary_tag_id, ingredient_id) VALUES (16, 5, 32);

INSERT INTO user_dietary_tags (id, user_id, dietary_tag_id) VALUES (1, 3, 2);
INSERT INTO user_dietary_tags (id, user_id, dietary_tag_id) VALUES (2, 3, 3);
INSERT INTO user_dietary_tags (id, user_id, dietary_tag_id) VALUES (3, 7, 3);
INSERT INTO user_dietary_tags (id, user_id, dietary_tag_id) VALUES (4, 8, 4);

INSERT INTO recipes (id, creator_id, title, thumbnail_url, description, preparation_time, serving_size, average_rating, total_reviews, status, created_at)
VALUES (1, 4, 'Nasi Goreng Kampung Pedas', 'http://example.com/nasgor.jpg', 'Nasi goreng tradisional dengan cita rasa pedas mantap', 15, 1, 4.5, 1, 'PUBLISHED', NOW());
INSERT INTO recipes (id, creator_id, title, thumbnail_url, description, preparation_time, serving_size, average_rating, total_reviews, status, created_at)
VALUES (2, 2, 'Tumis Ayam Brokoli Sehat', 'http://example.com/ayambrokoli.jpg', 'Menu diet tinggi protein dan rendah kalori', 20, 2, 4.5, 2, 'PUBLISHED', NOW());
INSERT INTO recipes (id, creator_id, title, thumbnail_url, description, preparation_time, serving_size, average_rating, total_reviews, status, created_at)
VALUES (3, 2, 'Soup Tahu Sutera Vegan', 'http://example.com/souptahu.jpg', 'Sup hangat menyehatkan ramah vegetarian dan vegan', 15, 3, 5.0, 1, 'PUBLISHED', NOW());
INSERT INTO recipes (id, creator_id, title, thumbnail_url, description, preparation_time, serving_size, average_rating, total_reviews, status, created_at)
VALUES (4, 1, 'Babi Kecap Manis Gurih', 'http://example.com/babikecap.jpg', 'Olahan daging babi lembut bumbu meresap', 45, 4, 0.0, 0, 'PUBLISHED', NOW());
INSERT INTO recipes (id, creator_id, title, thumbnail_url, description, preparation_time, serving_size, average_rating, total_reviews, status, created_at)
VALUES (5, 5, 'Steak Sirloin Lada Hitam Keto', 'http://example.com/steakketo.jpg', 'Steak premium khusus diet keto tinggi lemak sehat', 25, 1, 4.8, 2, 'PUBLISHED', NOW());
INSERT INTO recipes (id, creator_id, title, thumbnail_url, description, preparation_time, serving_size, average_rating, total_reviews, status, created_at)
VALUES (6, 5, 'Nasi Merah Tumis Jamur Diet', 'http://example.com/nasimerahjamur.jpg', 'Menu sehat rendah kalori penunjang diet seimbang', 20, 2, 4.0, 1, 'PUBLISHED', NOW());
INSERT INTO recipes (id, creator_id, title, thumbnail_url, description, preparation_time, serving_size, average_rating, total_reviews, status, created_at)
VALUES (7, 2, 'Udang Kukus Jahe Wangi', 'http://example.com/udangkukus.jpg', 'Olahan seafood segar minim minyak kaya nutrisi', 15, 2, 0.0, 0, 'PUBLISHED', NOW());

INSERT INTO recipe_steps (id, recipe_id, step_number, instruction) VALUES (1, 1, 1, 'Haluskan bawang putih dan cabai rawit.');
INSERT INTO recipe_steps (id, recipe_id, step_number, instruction) VALUES (2, 1, 2, 'Panaskan minyak goreng kelapa di wajan teflon, tumis bumbu halus hingga harum.');
INSERT INTO recipe_steps (id, recipe_id, step_number, instruction) VALUES (3, 1, 3, 'Masukkan nasi, kecap manis, garam, dan penyedap rasa jamur. Aduk rata menggunakan spatula kayu.');
INSERT INTO recipe_steps (id, recipe_id, step_number, instruction) VALUES (4, 2, 1, 'Potong dadu daging ayam dan siangi brokoli per kuntum.');
INSERT INTO recipe_steps (id, recipe_id, step_number, instruction) VALUES (5, 2, 2, 'Cincang bawang putih dan bawang bombai, tumis dengan minyak zaitun hingga harum.');
INSERT INTO recipe_steps (id, recipe_id, step_number, instruction) VALUES (6, 2, 3, 'Masukkan ayam hingga berubah warna, tambahkan brokoli, garam, and sedikit kecap.');
INSERT INTO recipe_steps (id, recipe_id, step_number, instruction) VALUES (7, 3, 1, 'Didihkan air di panci sup, masukkan bawang putih geprek.');
INSERT INTO recipe_steps (id, recipe_id, step_number, instruction) VALUES (8, 3, 2, 'Potong tahu putih dan wortel berbentuk bulat, masukkan ke dalam air mendidih.');
INSERT INTO recipe_steps (id, recipe_id, step_number, instruction) VALUES (9, 3, 3, 'Tambahkan garam dan minyak zaitun, masak hingga wortel empuk.');
INSERT INTO recipe_steps (id, recipe_id, step_number, instruction) VALUES (10, 4, 1, 'Potong daging babi ukuran sekali suap.');
INSERT INTO recipe_steps (id, recipe_id, step_number, instruction) VALUES (11, 4, 2, 'Tumis bawang putih, masukkan daging babi hingga mengencang.');
INSERT INTO recipe_steps (id, recipe_id, step_number, instruction) VALUES (12, 4, 3, 'Tuangkan kecap manis dalam jumlah banyak, tambahkan air, rebus dengan api kecil hingga kuah menyusut.');
INSERT INTO recipe_steps (id, recipe_id, step_number, instruction) VALUES (13, 5, 1, 'Lumuri daging sapi sirloin dengan minyak zaitun, garam, and lada hitam.');
INSERT INTO recipe_steps (id, recipe_id, step_number, instruction) VALUES (14, 5, 2, 'Panaskan panggangan barbeque hingga benar-benar panas.');
INSERT INTO recipe_steps (id, recipe_id, step_number, instruction) VALUES (15, 5, 3, 'Panggang daging selama 4 menit tiap sisi untuk tingkat kematangan medium.');
INSERT INTO recipe_steps (id, recipe_id, step_number, instruction) VALUES (16, 6, 1, 'Cuci beras merah lalu masa menggunakan rice cooker.');
INSERT INTO recipe_steps (id, recipe_id, step_number, instruction) VALUES (17, 6, 2, 'Iris bawang putih, bawang bombai, dan jamur kancing di talenan kayu.');
INSERT INTO recipe_steps (id, recipe_id, step_number, instruction) VALUES (18, 6, 3, 'Tumis bumbu iris dengan minyak wijen, masukkan jamur dan kecap asin.');
INSERT INTO recipe_steps (id, recipe_id, step_number, instruction) VALUES (19, 7, 1, 'Bersihkan udang dan lumuri dengan perasan jeruk nipis untuk menghilangkan bau amis.');
INSERT INTO recipe_steps (id, recipe_id, step_number, instruction) VALUES (20, 7, 2, 'Tata udang di wadah tahan panas, taburi dengan jahe iris dan daun bawang.');
INSERT INTO recipe_steps (id, recipe_id, step_number, instruction) VALUES (21, 7, 3, 'Kukus di dalam panci kukus yang sudah mendidih selama 10 menit.');

INSERT INTO recipe_ingredients (id, recipe_id, ingredient_id, amount, unit) VALUES (1, 1, 3, 2, 'siung');
INSERT INTO recipe_ingredients (id, recipe_id, ingredient_id, amount, unit) VALUES (2, 1, 18, 5, 'buah');
INSERT INTO recipe_ingredients (id, recipe_id, ingredient_id, amount, unit) VALUES (3, 1, 5, 2, 'sdm');
INSERT INTO recipe_ingredients (id, recipe_id, ingredient_id, amount, unit) VALUES (4, 1, 4, 1.5, 'sdm');
INSERT INTO recipe_ingredients (id, recipe_id, ingredient_id, amount, unit) VALUES (5, 1, 2, 0.5, 'sdt');
INSERT INTO recipe_ingredients (id, recipe_id, ingredient_id, amount, unit) VALUES (6, 1, 7, 1, 'sdt');
INSERT INTO recipe_ingredients (id, recipe_id, ingredient_id, amount, unit) VALUES (7, 2, 9, 250, 'gram');
INSERT INTO recipe_ingredients (id, recipe_id, ingredient_id, amount, unit) VALUES (8, 2, 14, 150, 'gram');
INSERT INTO recipe_ingredients (id, recipe_id, ingredient_id, amount, unit) VALUES (9, 2, 3, 2, 'siung');
INSERT INTO recipe_ingredients (id, recipe_id, ingredient_id, amount, unit) VALUES (10, 2, 12, 0.5, 'buah');
INSERT INTO recipe_ingredients (id, recipe_id, ingredient_id, amount, unit) VALUES (11, 2, 17, 1, 'sdm');
INSERT INTO recipe_ingredients (id, recipe_id, ingredient_id, amount, unit) VALUES (12, 2, 7, 0.5, 'sdt');
INSERT INTO recipe_ingredients (id, recipe_id, ingredient_id, amount, unit) VALUES (13, 3, 15, 1, 'kotak');
INSERT INTO recipe_ingredients (id, recipe_id, ingredient_id, amount, unit) VALUES (14, 3, 13, 1, 'buah');
INSERT INTO recipe_ingredients (id, recipe_id, ingredient_id, amount, unit) VALUES (15, 3, 3, 3, 'siung');
INSERT INTO recipe_ingredients (id, recipe_id, ingredient_id, amount, unit) VALUES (16, 3, 7, 1, 'sdt');
INSERT INTO recipe_ingredients (id, recipe_id, ingredient_id, amount, unit) VALUES (17, 4, 8, 500, 'gram');
INSERT INTO recipe_ingredients (id, recipe_id, ingredient_id, amount, unit) VALUES (18, 4, 4, 5, 'sdm');
INSERT INTO recipe_ingredients (id, recipe_id, ingredient_id, amount, unit) VALUES (19, 4, 3, 4, 'siung');
INSERT INTO recipe_ingredients (id, recipe_id, ingredient_id, amount, unit) VALUES (20, 5, 21, 200, 'gram');
INSERT INTO recipe_ingredients (id, recipe_id, ingredient_id, amount, unit) VALUES (21, 5, 17, 2, 'sdm');
INSERT INTO recipe_ingredients (id, recipe_id, ingredient_id, amount, unit) VALUES (22, 5, 7, 1, 'sdt');
INSERT INTO recipe_ingredients (id, recipe_id, ingredient_id, amount, unit) VALUES (23, 5, 25, 1, 'sdt');
INSERT INTO recipe_ingredients (id, recipe_id, ingredient_id, amount, unit) VALUES (24, 6, 22, 150, 'gram');
INSERT INTO recipe_ingredients (id, recipe_id, ingredient_id, amount, unit) VALUES (25, 6, 29, 100, 'gram');
INSERT INTO recipe_ingredients (id, recipe_id, ingredient_id, amount, unit) VALUES (26, 6, 3, 2, 'siung');
INSERT INTO recipe_ingredients (id, recipe_id, ingredient_id, amount, unit) VALUES (27, 6, 12, 0.25, 'buah');
INSERT INTO recipe_ingredients (id, recipe_id, ingredient_id, amount, unit) VALUES (28, 6, 24, 1, 'sdm');
INSERT INTO recipe_ingredients (id, recipe_id, ingredient_id, amount, unit) VALUES (29, 6, 23, 1, 'sdm');
INSERT INTO recipe_ingredients (id, recipe_id, ingredient_id, amount, unit) VALUES (30, 7, 32, 300, 'gram');
INSERT INTO recipe_ingredients (id, recipe_id, ingredient_id, amount, unit) VALUES (31, 7, 33, 1, 'buah');
INSERT INTO recipe_ingredients (id, recipe_id, ingredient_id, amount, unit) VALUES (32, 7, 35, 2, 'jempol');
INSERT INTO recipe_ingredients (id, recipe_id, ingredient_id, amount, unit) VALUES (33, 7, 34, 1, 'batang');

INSERT INTO recipe_ingredient_substitutions (id, recipe_ingredient_id, substitute_ingredient_id, amount, unit)
VALUES (1, 3, 17, 2, 'sdm');
INSERT INTO recipe_ingredient_substitutions (id, recipe_ingredient_id, substitute_ingredient_id, amount, unit)
VALUES (2, 11, 20, 1, 'sdm');
INSERT INTO recipe_ingredient_substitutions (id, recipe_ingredient_id, substitute_ingredient_id, amount, unit)
VALUES (3, 28, 17, 1, 'sdm');

INSERT INTO recipe_pantries (id, recipe_id, pantry_id) VALUES (1, 1, 1);
INSERT INTO recipe_pantries (id, recipe_id, pantry_id) VALUES (2, 1, 2);
INSERT INTO recipe_pantries (id, recipe_id, pantry_id) VALUES (3, 2, 1);
INSERT INTO recipe_pantries (id, recipe_id, pantry_id) VALUES (4, 2, 5);
INSERT INTO recipe_pantries (id, recipe_id, pantry_id) VALUES (5, 3, 6);
INSERT INTO recipe_pantries (id, recipe_id, pantry_id) VALUES (6, 4, 1);
INSERT INTO recipe_pantries (id, recipe_id, pantry_id) VALUES (7, 5, 8);
INSERT INTO recipe_pantries (id, recipe_id, pantry_id) VALUES (8, 5, 9);
INSERT INTO recipe_pantries (id, recipe_id, pantry_id) VALUES (9, 6, 9);
INSERT INTO recipe_pantries (id, recipe_id, pantry_id) VALUES (10, 7, 10);
INSERT INTO recipe_pantries (id, recipe_id, pantry_id) VALUES (11, 7, 9);

INSERT INTO recipe_dietary_tags (id, recipe_id, dietary_tag_id) VALUES (1, 1, 1);
INSERT INTO recipe_dietary_tags (id, recipe_id, dietary_tag_id) VALUES (2, 2, 1);
INSERT INTO recipe_dietary_tags (id, recipe_id, dietary_tag_id) VALUES (3, 3, 1);
INSERT INTO recipe_dietary_tags (id, recipe_id, dietary_tag_id) VALUES (4, 3, 2);
INSERT INTO recipe_dietary_tags (id, recipe_id, dietary_tag_id) VALUES (5, 3, 3);
INSERT INTO recipe_dietary_tags (id, recipe_id, dietary_tag_id) VALUES (6, 5, 1);
INSERT INTO recipe_dietary_tags (id, recipe_id, dietary_tag_id) VALUES (7, 5, 4);
INSERT INTO recipe_dietary_tags (id, recipe_id, dietary_tag_id) VALUES (8, 5, 3);
INSERT INTO recipe_dietary_tags (id, recipe_id, dietary_tag_id) VALUES (9, 5, 5);
INSERT INTO recipe_dietary_tags (id, recipe_id, dietary_tag_id) VALUES (10, 6, 1);
INSERT INTO recipe_dietary_tags (id, recipe_id, dietary_tag_id) VALUES (11, 6, 3);
INSERT INTO recipe_dietary_tags (id, recipe_id, dietary_tag_id) VALUES (12, 6, 5);
INSERT INTO recipe_dietary_tags (id, recipe_id, dietary_tag_id) VALUES (13, 7, 1);
INSERT INTO recipe_dietary_tags (id, recipe_id, dietary_tag_id) VALUES (14, 7, 3);

INSERT INTO reviews (id, user_id, recipe_id, rating, comment, created_at)
VALUES (1, 1, 2, 4, 'Rasanya enak sekali, brokoli renyah dan pas matangnya!', NOW());
INSERT INTO reviews (id, user_id, recipe_id, rating, comment, created_at)
VALUES (2, 3, 2, 5, 'Sangat membantu program diet defisit kalori saya!', NOW());
INSERT INTO reviews (id, user_id, recipe_id, rating, comment, created_at)
VALUES (3, 3, 3, 5, 'Sup vegan terfavorit saya saat ini, kuahnya segar.', NOW());
INSERT INTO reviews (id, user_id, recipe_id, rating, comment, created_at)
VALUES (4, 8, 5, 5, 'Sangat pas untuk menu makan malam keto saya, juicy bumbunya!', NOW());
INSERT INTO reviews (id, user_id, recipe_id, rating, comment, created_at)
VALUES (5, 6, 5, 4, 'Daging empuk, saus lada hitamnya meresap sempurna.', NOW());
INSERT INTO reviews (id, user_id, recipe_id, rating, comment, created_at)
VALUES (6, 7, 6, 4, 'Alternatif makan malam sehat yang tidak membosankan.', NOW());

INSERT INTO favorites (id, user_id, recipe_id, created_at) VALUES (1, 3, 2, NOW());
INSERT INTO favorites (id, user_id, recipe_id, created_at) VALUES (2, 3, 3, NOW());
INSERT INTO favorites (id, user_id, recipe_id, created_at) VALUES (3, 1, 2, NOW());
INSERT INTO favorites (id, user_id, recipe_id, created_at) VALUES (4, 8, 5, NOW());
INSERT INTO favorites (id, user_id, recipe_id, created_at) VALUES (5, 6, 5, NOW());
INSERT INTO favorites (id, user_id, recipe_id, created_at) VALUES (6, 7, 6, NOW());

INSERT INTO recipe_lists (id, name, owner_id, created_at) VALUES (1, 'Menu Diet Mingguan', 3, NOW());
INSERT INTO recipe_lists (id, name, owner_id, created_at) VALUES (2, 'Koleksi Protein Tinggi', 8, NOW());

INSERT INTO recipe_list_items (id, recipe_list_id, recipe_id) VALUES (1, 1, 2);
INSERT INTO recipe_list_items (id, recipe_list_id, recipe_id) VALUES (2, 1, 3);
INSERT INTO recipe_list_items (id, recipe_list_id, recipe_id) VALUES (3, 2, 5);
INSERT INTO recipe_list_items (id, recipe_list_id, recipe_id) VALUES (4, 2, 2);

INSERT INTO user_follows (id, follower_id, followee_id, created_at) VALUES (1, 1, 2, NOW());
INSERT INTO user_follows (id, follower_id, followee_id, created_at) VALUES (2, 3, 2, NOW());
INSERT INTO user_follows (id, follower_id, followee_id, created_at) VALUES (3, 6, 5, NOW());
INSERT INTO user_follows (id, follower_id, followee_id, created_at) VALUES (4, 8, 5, NOW());
INSERT INTO user_follows (id, follower_id, followee_id, created_at) VALUES (5, 7, 2, NOW());

INSERT INTO audit_logs (id, actor_id, action, target_type, target_id, details, ip_address, created_at)
VALUES (1, 2, 'UPLOAD_RECIPE', 'RECIPE', 2, 'Chef Arnold mengunggah resep Tumis Ayam Brokoli Sehat', '192.168.1.15', NOW());
INSERT INTO audit_logs (id, actor_id, action, target_type, target_id, details, ip_address, created_at)
VALUES (2, 2, 'UPLOAD_RECIPE', 'RECIPE', 3, 'Chef Arnold mengunggah resep Soup Tahu Sutera Vegan', '192.168.1.15', NOW());
INSERT INTO audit_logs (id, actor_id, action, target_type, target_id, details, ip_address, created_at)
VALUES (3, 3, 'ADD_FAVORITE', 'FAVORITE', 1, 'Clara menambahkan resep ID 2 ke daftar favoritnya', '172.16.25.4', NOW());
INSERT INTO audit_logs (id, actor_id, action, target_type, target_id, details, ip_address, created_at)
VALUES (4, 5, 'UPLOAD_RECIPE', 'RECIPE', 5, 'Chef Juna mengunggah resep Steak Sirloin Lada Hitam Keto', '192.168.1.88', NOW());
INSERT INTO audit_logs (id, actor_id, action, target_type, target_id, details, ip_address, created_at)
VALUES (5, 5, 'UPLOAD_RECIPE', 'RECIPE', 6, 'Chef Juna mengunggah resep Nasi Merah Tumis Jamur Diet', '192.168.1.88', NOW());
INSERT INTO audit_logs (id, actor_id, action, target_type, target_id, details, ip_address, created_at)
VALUES (6, 8, 'ADD_FAVORITE', 'FAVORITE', 4, 'Keto Bambang memfavoritkan resep steak keto', '10.0.2.15', NOW());

INSERT INTO notifications (id, user_id, title, message, is_read, created_at)
VALUES (1, 1, 'Resep Baru!', 'Kreator yang Anda ikuti, chef_arnold, baru saja mengunggah resep Soup Tahu Sutera Vegan!', false, NOW());
INSERT INTO notifications (id, user_id, title, message, is_read, created_at)
VALUES (2, 3, 'Resep Baru!', 'Kreator yang Anda ikuti, chef_arnold, baru saja mengunggah resep Soup Tahu Sutera Vegan!', false, NOW());
INSERT INTO notifications (id, user_id, title, message, is_read, created_at)
VALUES (3, 6, 'Resep Baru!', 'Kreator chef_juna mengunggah resep Steak Sirloin Lada Hitam Keto', false, NOW());
INSERT INTO notifications (id, user_id, title, message, is_read, created_at)
VALUES (4, 8, 'Resep Baru!', 'Kreator chef_juna mengunggah resep Steak Sirloin Lada Hitam Keto', false, NOW());