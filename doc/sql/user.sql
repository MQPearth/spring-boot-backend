CREATE TABLE `user_profile`
(
    `id`          bigint(0)                                                     NOT NULL,
    `deleted`     tinyint(0)                                                    NOT NULL DEFAULT 0,
    `create_time` datetime(3)                                                   NOT NULL,
    `modify_time` datetime(3)                                                   NULL     DEFAULT NULL,
    `creator`     bigint(0)                                                     NOT NULL,
    `modifier`    bigint(0)                                                     NULL     DEFAULT NULL,
    `url`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '个人网址',
    `true_name`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '真实姓名',
    `gender`      smallint(0)                                                   NOT NULL COMMENT '性别',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;