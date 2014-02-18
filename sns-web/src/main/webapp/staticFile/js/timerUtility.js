var timerUtility=function(){
   var TimerFunArray= {};
   var Timer= null;
   return{
    StartTimer: function () {
        Timer = setInterval(function () {
            for (var name in TimerFunArray) {
                TimerFunArray[name]();
            }
        }, 1000);
    },
    StopTimer: function () {
        clearInterval(Timer);
        Timer=null;
    },
    AddFunc:function(name,func){
    	TimerFunArray[name]=func;
    },
    DelFunc:function(name){
    	TimerFunArray[name]=undefined;
    },
    IsStart:function(){
    	return Timer?true:false;
    }
    };
}();