ALTER TABLE unik.posts MODIFY COLUMN post_text varchar(7000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL;
INSERT INTO posts (id, topic, post_text, created_dt)
VALUES (1, 'Flower', 'Flower - это веб-интерфейс и инструмент наблюдения для Celery, популярной системы распределенной обработки задач в Python. Он предоставляет пользовательский интерфейс для мониторинга и управления работой и производительностью Celery-кластера.
С использованием Flower вы можете:
1. Мониторить задачи: Flower отображает текущую активность задач, позволяя вам видеть запущенные, выполняющиеся и завершенные задачи. Вы также можете отслеживать статус и результат выполнения каждой задачи.
2. Управлять рабочими процессами: Вы можете просматривать список доступных рабочих процессов и машин в вашем Celery-кластере. Это позволяет вам контролировать количество активных рабочих процессов и масштабировать систему при необходимости.
3. Мониторить производительность: Flower предоставляет информацию о производительности вашего Celery-кластера, включая общую нагрузку, среднее время выполнения задач и другие метрики. Это помогает идентифицировать узкие места и оптимизировать работу системы.
4. Анализировать очереди задач: Вы можете просматривать состояние и размеры очередей задач, определять задержки в обработке, а также контролировать их состояние и доступность.
5. Мониторить сигналы Celery: Flower предоставляет информацию о состоянии Celery-кластера и сигналах, таких как сигналы продолжения выполнения (heartbeat) и сигналы, указывающие на изменения статуса задач.
Flower обеспечивает удобный и интуитивно понятный интерфейс для визуализации и управления вашими Celery-задачами, что делает его полезным инструментом для разработчиков, которые используют Celery для обработки задач в своих проектах.', '2024-01-01'),
       (2, 'Банки и отпечатки', 'В России несколько банков предлагают решения с поддержкой оплаты по отпечатку пальца. Вот некоторые из них:
1. Сбербанк. В рамках своего мобильного приложения "Сбербанк Онлайн" банк предоставляет функциональность, позволяющую пользователю привязать свои банковские карты и использовать отпечаток пальца для авторизации платежей в интернете и при покупках в офлайн-магазинах с помощью терминалов с поддержкой этой функции.
2. "Тинькофф Банк". У этого банка также есть функция оплаты с помощью отпечатка пальца. Пользователи мобильного приложения могут привязать свои карты и использовать сканер отпечатков пальцев для безопасной авторизации платежей.
3. "Альфа-Банк". Банк предлагает использование технологии оплаты по отпечатку пальца через свое мобильное приложение "Альфа-Банк Онлайн". Пользователи могут авторизовывать платежи, положив палец на сканер на терминале или с помощью специального устройства, подключаемого к смартфону.
Это лишь несколько примеров банков, предлагающих оплату по отпечатку пальца. Однако, стоит отметить, что доступность и использование таких технологий могут изменяться со временем, поэтому рекомендуется обращаться к конкретным банкам для получения актуальной информации о наличии подобных возможностей.', '2024-01-02')
;
INSERT INTO cargo (delivery_date, shipment_date, id, cargo_content, cargo_name, delivery_city, shipment_city)
VALUES ('2024-02-26', '2022-01-26', 1, 'donats', 'cargoDonats', 'Moscow', 'Syzran'),
       ('2023-03-26', '2021-01-26', 2, 'slippers', 'cargoSlippers', 'Moscow', 'Minsk'),
       ('2023-01-26', '2021-01-21', 3, 'knifes', 'cargoKnifes', 'Moscow', 'Novosibirsk'),
       ('2023-01-26', '2021-01-21', 4, 'knifes', 'cargoKnifes2', 'Syzran', 'Samara')
;
INSERT INTO users (id, password, role, username)
VALUES (1, '3c9909afec25354d551dae21590bb26e38d53f2173b8d3dc3eee4c047e7ab1c1eb8b85103e3be7ba613b31bb5c9c36214dc9f14a42fd7a2fdb84856bca5c44c2', 'Admin', 'admin'),
       (2, '3c9909afec25354d551dae21590bb26e38d53f2173b8d3dc3eee4c047e7ab1c1eb8b85103e3be7ba613b31bb5c9c36214dc9f14a42fd7a2fdb84856bca5c44c2', 'Creator', 'creator'),
       (3, '3c9909afec25354d551dae21590bb26e38d53f2173b8d3dc3eee4c047e7ab1c1eb8b85103e3be7ba613b31bb5c9c36214dc9f14a42fd7a2fdb84856bca5c44c2', 'Viewer', 'viewer'),
       (4, '3c9909afec25354d551dae21590bb26e38d53f2173b8d3dc3eee4c047e7ab1c1eb8b85103e3be7ba613b31bb5c9c36214dc9f14a42fd7a2fdb84856bca5c44c2', 'Viewer', 'tester'),
       (5, '3c9909afec25354d551dae21590bb26e38d53f2173b8d3dc3eee4c047e7ab1c1eb8b85103e3be7ba613b31bb5c9c36214dc9f14a42fd7a2fdb84856bca5c44c2', 'Creator', 'Andrey'),
       (6, '3c9909afec25354d551dae21590bb26e38d53f2173b8d3dc3eee4c047e7ab1c1eb8b85103e3be7ba613b31bb5c9c36214dc9f14a42fd7a2fdb84856bca5c44c2', 'Creator', 'Nikita'),
       (7, '3c9909afec25354d551dae21590bb26e38d53f2173b8d3dc3eee4c047e7ab1c1eb8b85103e3be7ba613b31bb5c9c36214dc9f14a42fd7a2fdb84856bca5c44c2', 'Creator', 'Pavel')
;
INSERT INTO user_posts_owner (user_id, post_id)
VALUES (5, 1),
       (6, 1),
       (7, 2)
;
