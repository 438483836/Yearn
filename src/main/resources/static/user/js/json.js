(function () {
    var $config = {
        offline: false,         // 离线模式
        develop: true,         // 开发者模式
        log: {
            debug: true,
            info: true
        },
        // 跨域请求
        jsonp: {
            enable:true,
            settings: {
                ip: "http://hc.support.neol.cc/",
                // ip: "http://192.168.1.202/",
                // ip: "http://tianyuanlife.neol.cc/",
                port: "",
                project: ""
            }
        },
        // 进度条
        loading: {
            enable: true,
            show: function () { // 显示进度条
                var show = window.show_loading_bar;
                show && show({
                    pct: 70,
                    finish: function () {
                        show({pct: 90, delay: 5});
                    }
                });
            },
            hide: function (callback) { // 隐藏进度条
                var show = window.show_loading_bar;
                if (show) {
                    show({
                        delay: .5,
                        pct: 100,
                        finish: function () {
                            callback && callback();
                }
                    });
                } else {
                    callback && callback();
                }
            }
        }
    };
    $config.jsonp.url = $config.jsonp.settings.ip + $config.jsonp.settings.port + $config.jsonp.settings.project;
    /**
     * JSON解析
     */
    window.$JSON = {
        // 配置
        config: function (c) {
            $.extend(true, $config, c);
        },
        log: {
            d: function (s) {
                if (!$config.log.debug) return;
                window.console && console.log(s);
            },
            i: function (s) {
                if (!$config.log.info) return;
                window.console && console.log(s);
            },
            s: function (s, t) {
                window.toastr && toastr.success(s, t || "成功!") || $JSON.log.d(s);
            },
            info: function (s, t) {
                window.toastr && toastr.info(s, t || "纳尔科技提醒您:") || alert(s);
            },
            e: function (s, t) {
                window.toastr && toastr.error(s, t || "出错啦!") || alert(s);
            }
        },
        /**
         * 类似jQuery的AJAX, 仅修改了success和error方法(success必填), 增加了develop变量
         *
         * @param settings
         * @see boolean develop; // 是否使用开发者模式(查看JSON字符串、开发者提示), 默认false
         * @see function success(object, array); // 对应JSON数据文档中的object、 array
         * @see function error(result); // 对应JSON数据文档中的result
         */
        ajax: function (settings) {// settings是传进来的方法
            //console.log('options');
            //console.log(options);
            // console.log("settings.nobar");
            // console.log(settings);
            // alert(settings.nobar);
            //var settings = $.extend({}, options);
            if (!settings)
                throw new Error("参数不能为空!");
            if (!settings.url)
                throw new Error("url不能为空!");
            if ($config.jsonp.enable) {
                settings.dataType = "jsonp";
                settings.jsonp = "callback";
                settings.url = $config.jsonp.url + settings.url;
            } else if (!settings.datatype) {
                settings.datatype = "json";
            }
            if (!settings.jQueryParam) // 是否使用jQuery.param(jQuery默认的拼接参数方式)
                settings.data = $JSON.paramStruts2(settings.data);
            // 开发者模式
            var isDevelop = $config.develop || settings.develop;
            if (isDevelop) {
                $JSON.log.i('\r\n请求路径：' + settings.url);
                $JSON.log.i('请求参数：' + $JSON.tostring(settings.data) || '无');
                $JSON.log.d('完整路径：' + settings.url + '?' + settings.data);
            }
            // 克隆参数至成功调用
            var successParams = $.extend(true, {}, settings, {develop: isDevelop});
            // 请求成功
            settings.success = function (data, textStatus, XHR) {
                $JSON.ajaxAfter(function () {
                    $JSON.requestSuccess(data, successParams);
                    $config.loading.enable && settings.$scope && settings.$scope.$apply();
                }, settings.nobar);
            };
            // 请求失败
            settings.error = $JSON.requestError;
            $JSON.ajaxBefore(settings.nobar);
            $config.offline && settings.success({result: {code: 0}}) || $.ajax(settings);
        },
        /**
         * 请求成功时执行
         *
         * @param data
         * @param success
         * @param error
         */
        requestSuccess: function (data, settings) {
            settings.develop && console.log('AJAX访问成功') || $JSON.log.d("返回JSON：\r\n" + $JSON.tostring(data));
            var response = $JSON.tojson(data);
            if (!(response && response.result)) { // 访问异常、服务器异常或没有响应结果
                $JSON.log.d("AJAX解析失败，没有响应结果！");
                return;
            }
            var success = settings instanceof Function ? settings : settings.success;
            if (response.result.notError) { // 正确结果
                if (success) { // 使用success方法处理结果
                    success(response.object, response.array);
                } else { // 提示传入success方法
                    $JSON.log.d("AJAX执行成功，但没有任何操作，传入success方法进行操作！");
                }
            } else { // 错误结果
                if (settings.error) { // 使用error方法处理错误
                    settings.error(response.result);
                } else { // 使用默认方法处理错误
                    if (settings.develop) {
                        console.log(
                            "AJAX执行成功，但服务器返回了一个错误。\r\n传入error方法可以处理错误，或关闭开发者模式仅向用户弹出提示信息。\r\n提示信息："
                            + response.result.info + "\r\n错误信息："
                            + $JSON.tostring(response.result));
                        $JSON.log.e(response.result.info);
                    } else {
                        $JSON.log.e(response.result.info);
                    }
                }
            }
        },
        /**
         * 请求失败
         *
         * @param XHR
         * @param textStatus
         * @param errorThrown
         */
        requestError: function (XHR, textStatus, errorThrown) {
            $JSON.ajaxAfter(function () {
                $JSON.log.e('错误码:' + textStatus + '.网络异常, 请稍后重试.');
            });
        },
        ajaxBefore: function (nobar) {
            //console.log("ajaxBefore nobar=" + nobar);
            if ($config.loading.enable && !nobar) {
                //console.log("show_loading_bar");
                $config.loading.show();
            }
        },
        ajaxAfter: function (callback, nobar) {
            // console.log("ajaxAfter nobar=" + nobar);
            if (!$config.loading.enable || nobar) {
                callback && callback();
                return;
            }
            // console.log("hide_loading_bar");
            $config.loading.hide(callback);
        },
        // jQuery load
        load: function (selector, url, callback, options) {
            $JSON.ajaxBefore();
            var $modal = $(selector);
            $modal.load(url, options, function () {
                $JSON.ajaxAfter(function () {
                    $modal.modal('show', options);
                    callback && callback($modal);
                });
            });
        },
        // Ajax队列
        ajaxQueue: function (settings) {
            if (!settings)
                throw new Error("参数不能为空!");
            var url = $JSON.verifyArray(settings, 'url', true);
            var runQueue = function (index) {
                if (index >= url.length) return;
                var params = getAjaxParam(settings, index);
                params.complete = function () {
                    runQueue(++index);
                };
                $JSON.ajax(params);
            };
            runQueue(0);
        },
        /**
         * 同时执行所有Ajax
         * @ 每完成一个执行whenComplete
         * @ 全部完成执行allComplete
         * @param settings
         */
        ajaxComplete: function (settings) {
            if (!settings)
                throw new Error("参数不能为空!");
            var url = $JSON.verifyArray(settings, 'url', true);
            var completed = 0;
            $.each(url, function (i) {
                var params = getAjaxParam(settings, i);
                params.complete = function () {
                    settings.whenComplete && settings.whenComplete(i);
                    completed++;
                    if (completed >= url.length)
                        settings.allComplete && settings.allComplete(i);
                };
                params.nobar = true;
                $JSON.ajax(params);
            });
        },
        verifyArray: function (param, name, required) {
            if (param && param[name] && $.isArray(param[name]))
                return param[name];
            if (required)
                throw new Error(name + '必须是数组.');
            return [];
        },
        // 请求对象
        Request: function (params, options) {
            //var self = options || {};
            var self = $.extend(true, {}, options);
            var $self = $(self);
            self.param = params || options.params || {};
            // 成功回调
            self.param.success = function (object, array) {
                $self.trigger('beforeSuccess', [object, array]);
                $self.trigger('success', [object, array]);
                $self.trigger('afterSuccess', [object, array]);
            };
            self.setURL = function (options) {
                self.param.url = options.namespace + '.' + options.method + '.action';
                return self;
            };
            self.setURL(options);
            self.data = function () {
                self.param.data = self.param.data || {};
                return self.param.data;
            };
            self.cs = function () {
                self.data().cs = self.data().cs || [];
                return self.param.data.cs;
            };
            self.addData = function (o) {
                if (!o) return;
                self.param.data = self.param.data || {};
                //self.param.data = $.extend(true, {}, self.param.data, o);
                $.extend(true, self.param.data, o);
                return self;
            };
            // 补充data参数, 优先使用原有参数
            self.patchData = function (o) {
                if (!o) return;
                self.param.data = self.param.data || {};
                self.param.data = $.extend(true, {}, o, self.param.data);
                return self;
            };
            self.addCondition = function (c) {
                self.cs().push(c);
                return self;
            };
            self.setParamMap = function (name, object) {
            };
            self.addSuccess = function (fn) {
                $self.on('success', function (e, object, array) {
                    fn(object, array);
                });
                return self;
            };
            self.afterSuccess = function (fn) {
                $self.on('afterSuccess', function (e, object, array) {
                    fn(object, array);
                });
                return self;
            };
            self.addSuccessEvent = function (event, fn) {
                $self.on(event, function (e, object, array) {
                    fn(object, array);
                });
                return self;
            };
            self.addErrorEvent = function (event, fn) {
                // 失败回调, 会覆盖$JSON的默认错误提示.
                if (!self.param.error) self.param.error = function (result) {
                    $self.trigger('error', [result]);
                };
                $self.on(event, function (e, error) {
                    fn(error);
                });
                return self;
            };
            self.getParam = function () {
                if (!self.param.url) throw new Error('$JSON.Parameter, 尚未指定URL!');
                if (!self.param.success) throw new Error('$JSON.Parameter, 尚未指定成功回调!');
                return $.extend(true, {}, self.param);
            };
            self.send = function () {
                $JSON.ajax(self.getParam());
            };
            return self;
        },
        /**
         * 解析json
         *
         * @param object
         * @returns
         */
        tojson: function (object) {
            // console.log(typeof object);
            switch (typeof object) {
                case "string":
                    return eval("(" + object + ")");
                case "object":
                default:
                    return object;
            }
        },
        /**
         * json转字符串
         *
         * @param object
         * @returns
         */
        tostring: function (object) {
            // console.log(typeof object);
            switch (typeof object) {
                case "string":
                    return object;
                case "object":
                default:
                    return JSON.stringify(object);
            }
        },
        /**
         * 将参数转为Struts2可识别的格式
         */
        paramStruts2: function (params, parent) {
            var paramUrl = '';
            var param = function (params, url, parent) {
                if (!params) return;
                if ($.isArray(params.value)) {
                    paramArray(params.value, url, parent);
                } else if ($.isPlainObject(params.value)) {
                    paramObject(params.value, url, parent);
                } else {
                    url += parent + '=' + ($.isNull(params.value) ? '' : params.value);
                }
                if (!url) return;
                if (paramUrl) {
                    paramUrl += '&' + url;
                } else {
                    paramUrl = url;
                }
            };
            var paramObject = function (object, url, parent) {
                if (!object) return;
                for (var name in object) {
                    param({name: name, value: object[name]}, url, parent ? parent + '.' + name : name);
                }
            };
            var paramArray = function (array, url, parent) {
                if (!array) return;
                if ($.isEmpty(array)) {
                    param({value: null}, url, parent);
                    return;
                }
                for (var index in array) {
                    param({name: index, value: array[index]}, url, parent + '[' + index + ']');
                }
            };
            param({value: params}, '', parent || '');
            return encodeURI(paramUrl);
        }
    };

    // 从Ajax数组中取单个Ajax需要的参数
    var getAjaxParam = function (object, i) {
        var newObject = {};
        for (var key in object) {
            var v = object[key];
            if ($.isArray(v)) {
                newObject[key] = v[i];
            } else {
                newObject[key] = v;
            }
        }
        return newObject;
    };
    // 拓展jQuery
    $.extend({
        isNull: function (v) {
            return v == null || v == undefined;
        },
        isEmpty: function (v) {
            return $.isNull(v) || !$.isArray(v) || v.length == 0;
        }
    });
})();