use spring2X;

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id 自增',
  `username` varchar(256) NOT NULL COMMENT '用户名',
  `nickname` varchar(256) DEFAULT NULL COMMENT '姓名',
  `description` varchar(512) DEFAULT NULL COMMENT '描述',
  `password` varchar(128) NOT NULL COMMENT '密码',
  `type` varchar(32) DEFAULT NULL COMMENT '类型',
  `is_enabled` tinyint(1) DEFAULT 1 NOT NULL COMMENT '状态，取值：1（启用）、0（禁用）',
  `email` tinyblob DEFAULT NULL COMMENT '邮箱',
  `telephone` tinyblob DEFAULT NULL COMMENT '电话号码',
  `is_deleted` tinyint(1) DEFAULT 0 NOT NULL COMMENT '是否删除，取值：1（已删除）、0（未删除）',
  `create_time` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户'