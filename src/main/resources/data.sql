INSERT INTO posts (id, topic, post_text, created_dt)
VALUES (1, 'Flower', 'Flower - это веб-интерфейс и инструмент наблюдения для Celery, популярной системы распределенной обработки задач в Python. Он предоставляет пользовательский интерфейс для мониторинга и управления работой и производительностью Celery-кластера.
С использованием Flower вы можете:
1. Мониторить задачи: Flower отображает текущую активность задач, позволяя вам видеть запущенные, выполняющиеся и завершенные задачи. Вы также можете отслеживать статус и результат выполнения каждой задачи.
2. Управлять рабочими процессами: Вы можете просматривать список доступных рабочих процессов и машин в вашем Celery-кластере. Это позволяет вам контролировать количество активных рабочих процессов и масштабировать систему при необходимости.
3. Мониторить производительность: Flower предоставляет информацию о производительности вашего Celery-кластера, включая общую нагрузку, среднее время выполнения задач и другие метрики. Это помогает идентифицировать узкие места и оптимизировать работу системы.
4. Анализировать очереди задач: Вы можете просматривать состояние и размеры очередей задач, определять задержки в обработке, а также контролировать их состояние и доступность.
5. Мониторить сигналы Celery: Flower предоставляет информацию о состоянии Celery-кластера и сигналах, таких как сигналы продолжения выполнения (heartbeat) и сигналы, указывающие на изменения статуса задач.
Flower обеспечивает удобный и интуитивно понятный интерфейс для визуализации и управления вашими Celery-задачами, что делает его полезным инструментом для разработчиков, которые используют Celery для обработки задач в своих проектах.', '2024-01-01')
;
INSERT INTO user_posts_owner (user_id, post_id)
VALUES (1, 1);
INSERT INTO cargo (delivery_date, shipment_date, id, cargo_content, cargo_name, delivery_city, shipment_city)
VALUES ('2024-02-26', '2022-01-26', 1, 'donats', 'cargoDonats', 'Moscow', 'Syzran'),
('2023-03-26', '2021-01-26', 2, 'slippers', 'cargoSlippers', 'Moscow', 'Minsk'),
('2023-01-26', '2021-01-21', 3, 'knifes', 'cargoKnifes', 'Moscow', 'Novosibirsk');
INSERT INTO users (password, role, username)
VALUES ('3c9909afec25354d551dae21590bb26e38d53f2173b8d3dc3eee4c047e7ab1c1eb8b85103e3be7ba613b31bb5c9c36214dc9f14a42fd7a2fdb84856bca5c44c2', 'Admin', 'admin'),
 ('3c9909afec25354d551dae21590bb26e38d53f2173b8d3dc3eee4c047e7ab1c1eb8b85103e3be7ba613b31bb5c9c36214dc9f14a42fd7a2fdb84856bca5c44c2', 'Creator', 'creator'),
 ('3c9909afec25354d551dae21590bb26e38d53f2173b8d3dc3eee4c047e7ab1c1eb8b85103e3be7ba613b31bb5c9c36214dc9f14a42fd7a2fdb84856bca5c44c2', 'Viewer', 'viewer'),
 ('3c9909afec25354d551dae21590bb26e38d53f2173b8d3dc3eee4c047e7ab1c1eb8b85103e3be7ba613b31bb5c9c36214dc9f14a42fd7a2fdb84856bca5c44c2', 'Viewer', 'tester'),
 ('3c9909afec25354d551dae21590bb26e38d53f2173b8d3dc3eee4c047e7ab1c1eb8b85103e3be7ba613b31bb5c9c36214dc9f14a42fd7a2fdb84856bca5c44c2', 'Creator', 'Andrey'),
 ('3c9909afec25354d551dae21590bb26e38d53f2173b8d3dc3eee4c047e7ab1c1eb8b85103e3be7ba613b31bb5c9c36214dc9f14a42fd7a2fdb84856bca5c44c2', 'Creator', 'Nikita'),
 ('3c9909afec25354d551dae21590bb26e38d53f2173b8d3dc3eee4c047e7ab1c1eb8b85103e3be7ba613b31bb5c9c36214dc9f14a42fd7a2fdb84856bca5c44c2', 'Creator', 'Pavel');