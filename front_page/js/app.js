(function (Vue) {

    let obj;
    let jsession;                                //cookies
    let domain = 'http://localhost:8080'         //域

    let util = new Vue({
        created() {
            obj = this;
        },
        methods: {
            /**
             * 是否登录
             */
            isLogin() {
                if (jsession == null)
                    return false;
                return true;
            },

            /**
             * 取得路径
             */
            getPath(path) {
                return domain + '/' + path + ';jsessionid=' + jsession
            },

            /**
             * 登录弹层
             */
            login() {
                let d = dialog({
                    width: 250,
                    title: '赏云后台登录',
                    content: document.getElementById('login_template').innerHTML,
                    okValue: '登录',
                    ok: function() {
                        let username = $('#username').val();
                        let password = $('#password').val();
                        obj.$http.post(domain + '/login',{username:username, password:password},{emulateJSON: true}).then(function(result){
                            let json = result.body;
                            if(json.code != 2000)
                                alert(json.msg);
                            jsession = json.cookies;
                            userLabel.username = username;
                            window.history.back();
                        },function(error){
                            console.log(error);
                            alert("登录失败");
                        });
                    },
                    cancelValue: "clean",
                    cancel: function() {
                        let username = $('#username').val('');
                        let password = $('#password').val('');
                        return false;
                    }
                });
                d.showModal();
            }
        }
    })

    let userLabel = new Vue({
        el:'#user-name-label',
        data:{
            username: '未登录'
        },
        methods: {
            logout:function () {
                this.$http.get(util.getPath('logout')).then(function(result){
                    console.log(result.body);
                    alert("登出成功");
                    userLabel.username = "未登录";
                },function (error) {
                    console.log(error);
                    alert("登出失败");
                })
            },
            login:function () {
                util.login();
            }
        }
    })

    //定义组件
    let article;
    const Article = {
        template:'#article_template',
        data: function () {
            return { articles:[] }
        },
        created() {
            if(!util.isLogin()){
                util.login();
                return false;
            }
            article = this;
            this.$options.methods.listArticle();
        },
        methods: {

            /**
             * 获取文章列表
             */
            listArticle() {
                article.$http.get(util.getPath('data/listArticle')).then(function(result){
                    article.articles = result.body;
                },function(error){
                    console.log(error);
                    alert("获取文章列表失败");
                });
            },

            /**
             * 添加文章
             */
            addApticle: function() {
                var d = dialog({
                    width: 260,
                    title: '添加文章',
                    content: document.getElementById('add_article_template').innerHTML,
                    okValue: '提交',
                    ok: function() {
                        var title = $("#article_title").val();
                        article.$http.post(util.getPath('data/addArticle'),{title:title},{emulateJSON: true}).then(function(result){
                            alert(result.body.msg);
                            this.listArticle();
                        },function(error){
                            console.log(error);
                            alert("文章提交失败");
                        });
                    },
                    cancelValue: '取消',
                    cancel: function() {}
                });
                d.showModal();
            },

            /**
             * 文章删除
             */
            deleteArticle: function(id) {
                article.$http.post(util.getPath('data/deleteArticle'),{id:id},{emulateJSON: true}).then(function(result){
                    alert(result.body.msg);
                    this.listArticle();
                },function(error){
                    console.log(error);
                    alert("文章删除失败");
                });
            }
        }
    }

    let admin;
    const Admin =  {
        template: '#admin_template',
        data: function() {
            return {admins:[]}
        },
        created: function() {
            if(!util.isLogin()){
                util.login();
                return false;
            }
            admin = this;
            this.$options.methods.listAdmin();
        },
        methods: {
            /**
             * 获取管理员列表
             */
            listAdmin() {
                admin.$http.get(util.getPath('data/listUserInfo')).then(function(result){
                    if (result.body.status == 403) {
                        alert(result.body.msg);
                        return;
                    }
                    admin.admins = result.body;
                }, function(error) {
                    console.log(error);
                    alert("获取管理员列表失败");
                })
            },

            /**
             * 添加管理员
             */
            addAdmin() {
                admin.$http.get(util.getPath('data/listRoleIN')).then(function(result){
                    for (var i = 0; i < result.body.length; i++)
                    {
                        $('#role_list').append('<label class="mr10"><input class="rl" type="checkbox" value="' + result.body[i].id + '">' + result.body[i].name + '</label>');
                    }
                }, function(error){
                    console.log(error);
                    alert("获取角色列表失败")
                });

                var d = dialog({
                    width: 300,
                    title: '添加管理员',
                    content: document.getElementById('add_admin_template').innerHTML,
                    okValue: '提交',
                    ok: function() {
                        var name = $('#adminName').val();
                        var password = $('#adminpassword').val();
                        var checkboxs = $('.rl:checkbox:checked');
                        var rls = new Array();
                        for (var i = 0; i < checkboxs.length; i++)
                        {
                            rls[i] = checkboxs[i].getAttribute('value');
                        }
                        admin.$http.post(util.getPath('data/addAdmin'),{name:name, password:password, rls:rls},{emulateJSON: true}).then(function(result){
                            alert(result.body.msg);
                            this.listAdmin();
                        },function(error){
                            console.log(error);
                            alert("管理员添加失败");
                        });
                    },
                    cancelValue: '取消',
                    cancel: function() {}
                });
                d.showModal();
            },

            /**
             * 删除管理员
             */
            deleteAdmin(id) {
                admin.$http.post(util.getPath('data/deleteUser'), {id:id}, {emulateJSON: true}).then(function(result) {
                    alert(result.body.msg);
                    this.listAdmin();
                }, function (error) {
                    console.log(error);
                    alert("管理员删除失败");
                })
            },

            /**
             * 更新管理员
             */
            updateAdmin(id) {
                admin.$http.get(util.getPath('data/listRoleIN')).then(function(result){
                    for (var i = 0; i < result.body.length; i++)
                    {
                        $('#update_role_list').append('<label class="mr10"><input class="rl" type="checkbox" value="' + result.body[i].id + '">' + result.body[i].name + '</label>');
                    }
                    this.findRole(id);
                }, function(error){
                    console.log(error);
                    alert("获取角色列表失败")
                });

                var d = dialog({
                    width: 300,
                    title: '修改管理员',
                    content: document.getElementById('update_admin_template').innerHTML,
                    okValue: '提交',
                    ok: function() {
                        var name = $('#update_adminName').val();
                        var checkboxs = $('.rl:checkbox:checked');
                        var rls = new Array();
                        for (var i = 0; i < checkboxs.length; i++)
                        {
                            rls[i] = checkboxs[i].getAttribute('value');
                        }
                        admin.$http.post(util.getPath('data/updateUser'),{userId:id, username:name, rls:rls},{emulateJSON: true}).then(function(result){
                            alert(result.body.msg);
                            this.listAdmin();
                        },function(error){
                            console.log(error);
                            alert("管理员修改失败");
                        });
                    },
                    cancelValue: '取消',
                    cancel: function() {}
                });
                d.showModal();
            },

            /**
             * 获取管理员角色信息
             */
            findRole: function(id) {
                admin.$http.post(util.getPath('data/findUser'),{id:id},{emulateJSON:true}).then(function(result){
                    $('#update_adminName').val(result.body.name);
                    var rl = result.body.roleList;
                    var checkboxs = $('.rl:checkbox');
                    for (var i = 0; i < rl.length; i++)
                    {
                        for (var j = 0; j < checkboxs.length; j++)
                        {
                            if (rl[i].id == checkboxs[j].getAttribute('value'))
                            {
                                checkboxs[j].checked = true;
                                break;
                            }
                        }
                    }
                },function (error) {
                    console.log(error);
                    alert("角色获取失败");
                })
            }
        }
    }

    let role;
    const Role = {
        template: '#role_template',
        data: function() {
            return {roles:[]}
        },
        created: function() {
            if(!util.isLogin()){
                util.login();
                return false;
            }
            role = this;
            this.$options.methods.listRole();
        },
        methods: {
            /**
             * 获取角色列表
             */
            listRole: function() {
                role.$http.get(util.getPath('data/listRole')).then(function(result) {
                    if (result.body.status == 403) {
                        alert(result.body.msg);
                        return;
                    }
                    role.roles = result.body;
                },function (error) {
                    console.log(error);
                    alert("获取角色列表失败");
                })
            },

            /**
             * 删除角色
             * @param id
             */
            deleteRole: function(id) {
                role.$http.post(util.getPath('data/deleteRole'), {id:id}, {emulateJSON: true}).then(function(result) {
                    alert(result.body.msg);
                    this.listRole();
                }, function (error) {
                    console.log(error);
                    alert("角色删除失败");
                })
            },

            /**
             * 修改角色
             */
            updateRole: function(id) {
                role.$http.get(util.getPath('data/listPermission')).then(function(result){
                    for (var i = 0; i < result.body.length; i++)
                    {
                        $('#permission_list').append('<label class="mr10"><input class="pl" type="checkbox" value="' + result.body[i].id + '">' + result.body[i].name + '</label>');
                    }
                    this.findRole(id);
                }, function(error){
                    console.log(error);
                    alert("获取权限列表失败")
                });

                var d = dialog({
                    width: 300,
                    title: '修改角色',
                    content: document.getElementById('add_role_template').innerHTML,
                    okValue: '提交',
                    ok: function() {
                        var roleName = $('#roleName').val();
                        var checkboxs = $('.pl:checkbox:checked');
                        var pls = new Array();
                        for (var i = 0; i < checkboxs.length; i++)
                        {
                            pls[i] = checkboxs[i].getAttribute('value');
                        }
                        role.$http.post(util.getPath('data/updateRole'),{roleId:id, roleName:roleName, pls:pls},{emulateJSON: true}).then(function(result){
                            alert(result.body.msg);
                            this.listRole();
                        },function(error){
                            console.log(error);
                            alert("角色修改失败");
                        });
                    },
                    cancelValue: '取消',
                    cancel: function() {}
                });
                d.showModal();
            },

            /**
             * 创建角色
             */
            addRole: function() {
                role.$http.get(util.getPath('data/listPermission')).then(function(result){
                    for (var i = 0; i < result.body.length; i++)
                    {
                        $('#permission_list').append('<label class="mr10"><input class="pl" type="checkbox" value="' + result.body[i].id + '">' + result.body[i].name + '</label>');
                    }
                }, function(error){
                    console.log(error);
                    alert("获取权限列表失败")
                });

                var d = dialog({
                    width: 300,
                    title: '添加角色',
                    content: document.getElementById('add_role_template').innerHTML,
                    okValue: '提交',
                    ok: function() {
                        let roleName = $('#roleName').val();
                        let checkboxs = $('.pl:checkbox:checked');
                        let pls = new Array();
                        for (var i = 0; i < checkboxs.length; i++)
                        {
                            pls[i] = checkboxs[i].getAttribute('value');
                        }
                        role.$http.post(util.getPath('data/addRole'),{roleName:roleName, pls:pls},{emulateJSON: true}).then(function(result){
                            alert(result.body.msg);
                            this.listRole();
                        },function(error){
                            console.log(error);
                            alert("角色提交失败");
                        });
                    },
                    cancelValue: '取消',
                    cancel: function() {}
                });
                d.showModal();
            },

            /**
             * 获取角色职能信息
             */
            findRole: function(id) {
                role.$http.post(util.getPath('data/findRole'),{id:id},{emulateJSON:true}).then(function(result){
                    $('#roleName').val(result.body.name);
                    var pl = result.body.permissionList;
                    var checkboxs = $('.pl:checkbox');
                    for (var i = 0; i < pl.length; i++)
                    {
                        for (var j = 0; j < checkboxs.length; j++)
                        {
                            if (pl[i].id == checkboxs[j].getAttribute('value'))
                            {
                                checkboxs[j].checked = true;
                                break;
                            }
                        }
                    }
                },function (error) {
                    console.log(error);
                    alert("角色获取失败");
                })
            }
        }
    }

    let menu;
    const Menu = {
        template: '#menu_template',
        data: function() {
            return { menus:[] }
        },
        created: function() {
            if(!util.isLogin()){
                util.login();
                return false;
            }
            menu = this;
            this.$options.methods.listMenu();
        },
        methods: {

            /**
             * 获取菜单记录列表
             */
            listMenu: function () {
                menu.$http.get(util.getPath('data/listMenu')).then(function(result){
                    if (result.body.status == 403) {
                        alert(result.body.msg);
                        return;
                    }
                    menu.menus = result.body;
                    for (var i = 0; i < menu.menus.length; i++) {
                        menu.menus[i].time = formatDate(menu.menus[i].time);
                    };
                },function(error){
                    console.log(error);
                    alert("获取菜单记录列表失败")
                })
            },

            /**
             * 删除菜单记录
             */
            deleteMenu: function(id) {
                menu.$http.post(util.getPath('data/deleteMenu'),{id:id},{emulateJSON:true}).then(function(result){
                    alert(result.body.msg);
                    console.log(result.body.msg);
                    this.listMenu();
                },function(error){
                    console.log(error);
                    alert("删除记录失败");
                })
            }
        }
    }

    const routes = [
        {path:'/article', component: Article},
        {path:'/admin', component: Admin},
        {path:'/role', component: Role},
        {path:'/menu', component: Menu},
    ]

    //创建路由器实例并传递`routes`选项
    const router = new VueRouter({
        routes
    })

    const app = new Vue({
        router
    }).$mount('#app')

}) (Vue)

function formatDate(time) {
    var d = new Date(time);
    return d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate() + ' ' + d.getHours() + ':' + d.getMinutes() + ':' + d.getSeconds();
};