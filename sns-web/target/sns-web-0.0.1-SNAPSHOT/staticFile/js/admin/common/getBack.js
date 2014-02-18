var getBack=function($ctrl,params){
	$.pdialog.closeCurrent();
	if(params.nameCtrl&&params.name){
		$("#"+params.nameCtrl).val(params.name);
	}
	if(params.valueCtrl&&params.id){
		$("#"+params.valueCtrl).val(params.id);
	}
};