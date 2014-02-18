var bindMovePhoto = function(img) {
	var x = y = 0;
	var $parent = $(img).parent().parent();
	var func=function(e) {
		
		var width = $(this).width();
		var height = $(this).height();
		if (img.width > img.height) {
			var left = parseInt($(img).css("marginLeft"))|0;
			if (x>0&&x < e.pageX) {
				if (width - left < img.width) {
					
					$(img).css("marginLeft", left-(e.pageX - x));

				}
			} else if (x > e.pageX) {
				if (left < 0) {

					$(img).css("marginLeft",
							left+(x - e.pageX));

				} 
			}
		} else if (img.width < img.height) {
			var top = parseInt($(img).css("marginTop"))|0;
			if (y>0&&y < e.pageY) {
				if (height - top < img.height) {
					
					$(img).css("marginTop", top-(e.pageY - y));

				}
			} else if (y > e.pageY) {
				if (top < 0) {

					$(img).css("marginTop",
							top+(y - e.pageY));

				} 
			}
		}
		
		x = e.pageX;
		y = e.pageY;
	};
	$parent.bind("mouseenter",function(e){
		x = e.pageX;
		y = e.pageY;
		$parent.bind("mousemove",func);
	}).bind("mouseleave",function(){
		$parent.unbind("mousemove",func);
	});
};

