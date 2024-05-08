		// 주소 정보를 가져오고
		var address = $('#address').val();


		var container = document.getElementById('map'); // div class="map"
		var options = {
		    center: new kakao.maps.LatLng(37.657185506031, 127.0625426693113), // 기준좌표 지정 (필수)
		    level: 3
		};

		var map = new kakao.maps.Map(container, options);

    function Postcode() {
        new daum.Postcode({
            oncomplete: function(data){
                var address = data.address;
            }
        })
    }

		var geocoder = new kakao.maps.services.Geocoder();

		geocoder.addressSearch(address, function (result, status) {
		    if (status === kakao.maps.services.Status.OK) {
		        var cords = new kakao.maps.LatLng(result[0].y, result[0].x)


		        map.setCenter(cords);

		        var marker = new kakao.maps.Marker({
		            map: map,
		            position: cords
		        });

		        marker.setMap(map);

		    } else {
		        console.error('주소 검색 실패');
		    }
		    console.log(result); // 주소가 출력되는지 확인하기 위해 작성해놓음(개발자에서 [..]배열창
		});