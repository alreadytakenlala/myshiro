-- 用户表 --
CREATE TABLE user (
  id int(11) NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL DEFAULT '',
  password VARCHAR(255) NOT NULL DEFAULT '',
  salt VARCHAR(255) NOT NULL DEFAULT '',
  PRIMARY KEY (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

-- 给用户名添加唯一约束 --
ALTER TABLE 'user' ADD unique('name');

-- 角色表 --
CREATE TABLE role (
  id int(11) NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL DEFAULT '',
  PRIMARY KEY (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

-- 权限表 --
CREATE TABLE permission (
  id int(11) NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL DEFAULT '',
  permission VARCHAR(255) NOT NULL DEFAULT '',
  PRIMARY KEY (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

-- 用户角色关系表 --
CREATE TABLE user_role (
  uid int(11) NOT NULL,
  rid int(11) NOT NULL,
  KEY (uid),
  KEY (rid)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

-- 添加用户角色表外键 --
ALTER TABLE user_role
ADD CONSTRAINT uid_user
FOREIGN KEY (uid)
REFERENCES user (id)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE user_role
ADD CONSTRAINT rid_user
FOREIGN KEY (rid)
REFERENCES role (id)
ON DELETE CASCADE
ON UPDATE CASCADE;

-- 角色权限关系表 --
CREATE TABLE role_permission (
  rid int(11) NOT NULL,
  pid int(11) NOT NULL,
  KEY (rid),
  KEY (pid)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

-- 添加角色权限表外键 --
ALTER TABLE role_permission
ADD CONSTRAINT rid_role
FOREIGN KEY (rid)
REFERENCES role (id)
ON DELETE CASCADE
ON UPDATE CASCADE;

ALTER TABLE role_permission
ADD CONSTRAINT pid_permission
FOREIGN KEY (pid)
REFERENCES permission (id)
ON DELETE CASCADE
ON UPDATE CASCADE;

-- 文章表 --
CREATE TABLE article (
  id int(11) NOT NULL AUTO_INCREMENT,
  title VARCHAR(255) NOT NULL DEFAULT '',
  content VARCHAR(255) DEFAULT '',
  PRIMARY KEY (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

-- 权限管理の菜单表 --
CREATE TABLE menu (
  id VARCHAR(255) NOT NULL,
  name VARCHAR(255) NOT NULL DEFAULT '',
  permission VARCHAR(255) NOT NULL DEFAULT '',
  time timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

-- 初始化职能 --
INSERT INTO permission (name, permission) VALUES ('添加', 'article:add');
INSERT INTO permission (name, permission) VALUES ('删除', 'artiele:delete');
INSERT INTO permission (name, permission) VALUES ('查询', 'artiele:select');
INSERT INTO permission (name, permission) VALUES ('添加', 'role:add');
INSERT INTO permission (name, permission) VALUES ('删除', 'role:delete');
INSERT INTO permission (name, permission) VALUES ('更新', 'role:update');
INSERT INTO permission (name, permission) VALUES ('查询', 'role:select');
INSERT INTO permission (name, permission) VALUES ('添加', 'admin:add');
INSERT INTO permission (name, permission) VALUES ('删除', 'admin:delete');
INSERT INTO permission (name, permission) VALUES ('更新', 'admin:update');
INSERT INTO permission (name, permission) VALUES ('查询', 'admin:select');
INSERT INTO permission (name, permission) VALUES ('查询', 'menu:select');
INSERT INTO permission (name, permission) VALUES ('删除', 'menu:delete');