function sortTable(n) {
	console.log(n);
	
	var table, rows, switching, o, x, y, shouldSwitch, dir, switchCnt = 0;
		table = document.getElementById("adminTable");
		switching = true;
		dir = "asc";
	
	while(switching){
		switching = false;
		rows = table.getElementsByTagName("tr");
	
		for(o=1; o<(rows.length-1); o++){
			shouldSwitch = false;
			
			x = rows[o].getElementsByTagName("td")[n];
			y = rows[o+1].getElementsByTagName("td")[n];
			
			if(n=='5') { // 결제금액일 때 
				if(dir=='asc') {
					if (parseInt(x.innerHTML) > parseInt(y.innerHTML)) {
						shouldSwitch = true;
						break;
					}
					
				} else if (dir=='desc') {
					if (parseInt(x.innerHTML) < parseInt(y.innerHTML)) {
						shouldSwitch = true;
						break;
					}
				}
				
			} else { // 다른 정렬
				if(dir=='asc') {
					if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
						shouldSwitch = true;
						break;
					}
					
				} else if (dir=='desc') {
					if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
						shouldSwitch = true;
						break;
					}
				}
			}
		}
		
		if(shouldSwitch) {
			rows[o].parentNode.insertBefore(rows[o+1], rows[o]);
			switching = true;
			switchCnt++;
		} else {
			if(switchCnt==0 && dir=='asc') {
				dir = 'desc';
				switching = true;
			}
		}
	}
}