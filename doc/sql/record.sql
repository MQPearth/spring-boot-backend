CREATE TABLE `user_modify_record`
(
    `id`          bigint(0)                                             NOT NULL,
    `deleted`     tinyint(0)                                            NOT NULL,
    `create_time` datetime(3)                                           NOT NULL,
    `modify_time` datetime(3)                                           NULL DEFAULT NULL,
    `creator`     bigint(0)                                             NOT NULL,
    `modifier`    bigint(0)                                             NULL DEFAULT NULL,
    `user_id`     bigint(0)                                             NOT NULL COMMENT '用户id',
    `old_data`    text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '旧数据',
    `new_data`    text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '新数据',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `user_id` (`user_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;