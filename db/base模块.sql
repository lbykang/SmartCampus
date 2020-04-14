-- -----------------------------------------------------
-- Table `citymis`.`basis_student`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `citymis`.`basis_student` (
  `id` BIGINT(18) NOT NULL COMMENT '主键',
  `study_id` VARCHAR(12) NOT NULL COMMENT '学号',
  `password` VARCHAR(12) NOT NULL COMMENT '密码（默认学号）',
  `salt` VARCHAR(32) NOT NULL COMMENT '盐加密',
  `name` VARCHAR(12) NOT NULL COMMENT '姓名',
  `gender` BIGINT(18) UNSIGNED NOT NULL COMMENT '性别（dic:1 男，0 女）',
  `profession` BIGINT(18) NOT NULL COMMENT '专业类别（FK）',
  `grade` VARCHAR(12) NOT NULL COMMENT '班级',
  `tel` VARCHAR(12) NOT NULL COMMENT '手机号码',
  `email` VARCHAR(16) NOT NULL COMMENT '电子邮箱',
  `people` BIGINT(18) NOT NULL COMMENT '名族类别(dic)',
  `political_status` BIGINT(18) NULL COMMENT '政治面貌类别(dic)',
  `id_card` VARCHAR(18) NOT NULL COMMENT '身份证号码',
  `home_town` VARCHAR(16) NOT NULL COMMENT '籍贯',
  `photo` VARCHAR(64) NULL COMMENT '照片（路径）',
  `gmt_admission` TIMESTAMP(6) NOT NULL COMMENT '入学时间',
  `is_enabled` BIGINT(18) UNSIGNED NOT NULL COMMENT '启用标识（dic: 1 表示是，0 表示否）',
  `creator` BIGINT(18) NOT NULL COMMENT '创建者（FK）',
  `gmt_create` TIMESTAMP(6) NOT NULL COMMENT '创建时间',
  `updater` BIGINT(18) NULL COMMENT '修改者（FK）',
  `gmt_modified` TIMESTAMP(6) NULL COMMENT '修改时间',
  `is_deleted` BIGINT(18) UNSIGNED NOT NULL COMMENT '删除标识（ dic:1 表示是，0 表示否）',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  UNIQUE INDEX `id_card_UNIQUE` (`id_card` ASC))
ENGINE = InnoDB
COMMENT = '系统模块-学生表';


-- -----------------------------------------------------
-- Table `citymis`.`basis_teacher`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `citymis`.`basis_teacher` (
  `id` BIGINT(18) NOT NULL COMMENT '主键',
  `work_id` VARCHAR(12) NOT NULL COMMENT '工号',
  `password` VARCHAR(12) NOT NULL COMMENT '密码（默认：工号）',
  `salt` VARCHAR(32) NOT NULL COMMENT '盐加密',
  `name` VARCHAR(12) NOT NULL COMMENT '姓名',
  `gender` BIGINT(18) UNSIGNED NOT NULL COMMENT '性别(dic: 1 表示男，0 表示女)',
  `college` BIGINT(18) NOT NULL COMMENT '院系类别（FK）',
  `tel` VARCHAR(12) NOT NULL COMMENT '手机号码',
  `landline` VARCHAR(12) NULL COMMENT '座机号码',
  `email` VARCHAR(16) NOT NULL COMMENT '电子邮箱',
  `people` BIGINT(18) UNSIGNED NOT NULL COMMENT '名族类别（dic）',
  `political_status` BIGINT(18) UNSIGNED NULL COMMENT '政治面貌类别（dic）',
  `id_card` VARCHAR(18) NOT NULL COMMENT '身份证号码',
  `home_town` VARCHAR(16) NOT NULL COMMENT '籍贯',
  `photo` VARCHAR(64) NULL COMMENT '照片路径',
  `education` BIGINT(18) UNSIGNED NOT NULL COMMENT '学历类别（dic）',
  `degree` BIGINT(18) UNSIGNED NOT NULL COMMENT '学位类别（dic）',
  `staff_type` BIGINT(18) NULL COMMENT '职工类别（dic）',
  `rank_type` BIGINT(18) NULL COMMENT '等级类别（dic）',
  `is_enabled` BIGINT(18) UNSIGNED NOT NULL COMMENT '启用标识（dic: 1 表示是，0 表示否）',
  `creator` BIGINT(18) NOT NULL COMMENT '创建者（FK）',
  `gmt_create` TIMESTAMP(6) NOT NULL COMMENT '创建时间',
  `updater` BIGINT(18) NULL COMMENT '修改者（FK）',
  `gmt_modified` TIMESTAMP(6) NULL COMMENT '修改时间',
  `is_deleted` BIGINT(18) UNSIGNED NOT NULL COMMENT '删除标识（ dic:1 表示是，0 表示否）',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `tel_UNIQUE` (`tel` ASC),
  UNIQUE INDEX `landline_UNIQUE` (`landline` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  UNIQUE INDEX `id_card_UNIQUE` (`id_card` ASC),
  UNIQUE INDEX `work_id_UNIQUE` (`work_id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `citymis`.`basis_college`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `citymis`.`basis_college` (
  `id` BIGINT(18) NOT NULL COMMENT '主键',
  `mark` VARCHAR(16) NOT NULL COMMENT '标识号',
  `name` VARCHAR(16) NOT NULL COMMENT '院系名称',
  `dean` BIGINT(18) NOT NULL COMMENT '院长（FK）',
  `contact` BIGINT(18) NOT NULL COMMENT '联系人（FK）',
  `sort` TINYINT(1) UNSIGNED NULL COMMENT '排序码',
  `is_enabled` BIGINT(18) UNSIGNED NOT NULL COMMENT '启用标识（dic: 1 表示是，0 表示否）',
  `creator` BIGINT(18) NOT NULL COMMENT '创建者（FK）',
  `gmt_create` TIMESTAMP(6) NOT NULL COMMENT '创建时间',
  `updater` BIGINT(18) NULL COMMENT '修改者（FK）',
  `gmt_modified` TIMESTAMP(6) NULL COMMENT '修改时间',
  `is_deleted` BIGINT(18) UNSIGNED NOT NULL COMMENT '删除标识（ dic:1 表示是，0 表示否）',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `mark_UNIQUE` (`mark` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `citymis`.`basis_profession`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `citymis`.`basis_profession` (
  `id` BIGINT(18) NOT NULL,
  `mark` VARCHAR(16) NOT NULL COMMENT '标识号',
  `name` VARCHAR(16) NOT NULL COMMENT '专业名称',
  `dean` BIGINT(18) NOT NULL COMMENT '专业主任',
  `contact` BIGINT(18) NOT NULL COMMENT '联系人',
  `sort` TINYINT(1) UNSIGNED NULL COMMENT '排序码',
  `is_enabled` BIGINT(18) UNSIGNED NOT NULL COMMENT '启用标识（dic: 1 表示是，0 表示否）',
  `creator` BIGINT(18) NOT NULL COMMENT '创建者（FK）',
  `gmt_create` TIMESTAMP(6) NOT NULL COMMENT '创建时间',
  `updater` BIGINT(18) NULL COMMENT '修改者（FK）',
  `gmt_modified` TIMESTAMP(6) NULL COMMENT '修改时间',
  `is_deleted` BIGINT(18) UNSIGNED NOT NULL COMMENT '删除标识（ dic:1 表示是，0 表示否）',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `mark_UNIQUE` (`mark` ASC))
ENGINE = InnoDB;