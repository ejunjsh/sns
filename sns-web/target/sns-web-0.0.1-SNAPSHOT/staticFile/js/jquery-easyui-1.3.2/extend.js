 $.extend($.fn.combo.methods, {
                /**
                 * 禁用combo文本域
                 * @param {Object} jq
                 * @param {Object} param stopArrowFocus:是否阻止点击下拉按钮时foucs文本域
                 * activeTextArrow:是否激活点击文本域也显示下拉列表
                 * stoptype:禁用类型，包含disable和readOnly两种方式
                 */
                disableTextbox: function(jq, param){
                    return jq.each(function(){
                        param = param || {};
                        var textbox = $(this).combo("textbox");
                        var that = this;
                        var panel = $(this).combo("panel");
                        var data = $(this).data('combo');
                        if (param.stopArrowFocus) {
                            data.stopArrowFocus = param.stopArrowFocus;
                            var arrowbox = $.data(this, 'combo').combo.find('span.combo-arrow');
                            arrowbox.unbind('click.combo').bind('click.combo', function(){
                                if (panel.is(":visible")) {
                                    $(that).combo('hidePanel');
                                }
                                else {
                                    $("div.combo-panel").panel("close");
                                    $(that).combo('showPanel');
                                }
                            });
                            textbox.unbind('mousedown.mycombo').bind('mousedown.mycombo', function(e){
                                e.preventDefault();
                            });
                        }
                        if (param.activeTextArrow) {
                            data.activeTextArrow = param.activeTextArrow;
                            textbox.bind('click.mycombo', function(){
                                if (panel.is(":visible")) {
                                    $(that).combo('hidePanel');
                                }
                                else {
                                    $("div.combo-panel").panel("close");
                                    $(that).combo('showPanel');
                                }
                            });
                        }
                        textbox.prop(param.stoptype ? param.stoptype : 'disabled', true);
                        data.stoptype = param.stoptype ? param.stoptype : 'disabled';
                    });
                },
                /**
                 * 还原文本域
                 * @param {Object} jq
                 */
                enableTextbox: function(jq){
                    return jq.each(function(){
                        var textbox = $(this).combo("textbox");
                        var data = $(this).data('combo');
                        if (data.stopArrowFocus) {
                            var that = this;
                            var panel = $(this).combo("panel");
                            var arrowbox = $.data(this, 'combo').combo.find('span.combo-arrow');
                            arrowbox.unbind('click.combo').bind('click.combo', function(){
                                if (panel.is(":visible")) {
                                    $(that).combo('hidePanel');
                                }
                                else {
                                    $("div.combo-panel").panel("close");
                                    $(that).combo('showPanel');
                                }
                                textbox.focus();
                            });
                            textbox.unbind('mousedown.mycombo');
                            data.stopArrowFocus = null;
                        }
                        if (data.activeTextArrow) {
                            textbox.unbind('click.mycombo');
                            data.activeTextArrow = null;
                        }
                        textbox.prop(data.stoptype, false);
                        data.stoptype = null;
                    });
                }
            });