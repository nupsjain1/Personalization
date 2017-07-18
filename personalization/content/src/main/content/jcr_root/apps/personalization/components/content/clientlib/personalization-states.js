$(document).ready(function(){

    var store = ContextHub.getStore("weatherstore");
    $("#state-select").on('change',function(event){
        var state = event.target.value;
        changeInStore(state);
    });

    function changeInStore(state){
        var data = {};
        data['appid'] = '96aa3a9f49ea5bd1c62d8340b795158a';
        data['q'] = state + ',in';
		$.ajax({
            url: "http://api.openweathermap.org/data/2.5/weather",
            data: data,
            success: function(result){
                store.setItem("data", result);
                store.setItem("state", state)
    		}
        });
    }
});



