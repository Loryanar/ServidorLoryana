function registrar(){

		    var json ={
		    		nombre: document.getElementById("name").value,
		    		apellido: document.getElementById("lastname").value,
		            email: document.getElementById("email").value,
		            contrasena: document.getElementById("passw").value,
		            edad: document.getElementById("age").value,
		            telf: document.getElementById("cell").value,
		            id: document.getElementById("ced").value,
		            us: document.getElementById("user").value
		    }
		    
		    
		    
		    let configs = {
		            method: 'post',
		            body: JSON.stringify(json),
		            withCredentials: true,
		            credentials: 'include',
		            headers: {
		                'Content-type': 'text'
		            }
		    }
		    
		    console.log(configs.body);
		    fetch('./registro', configs)
		        .then(res => res.json())
		        .then(data => {console.log(data)
		        	let userData = data.userData;
		            if(data.status == 200){
		            	alert("todo bien registro con exito");
		            }
		            	else{
		            		console.log("jaja kloko no");
		            	}
		            	
		    });
		}
	

	function checkEmail() {
	    var email = document.getElementById('email');
	    var mailFormat = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;

	    if (!mailFormat.test(email.value)) {
	        alert('Direccion de correo invalida');
	        email.focus;
	        return false;
	    }else{
	    	a = 1;
	    }
	}


	const check = () => {
		checkEmail();
		checkPassword();
		registrar();
		
	}
	function checkPassword(){
	    var password = document.getElementById('passw');
	    var passwordFormat= /(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}/;

	    if (!passwordFormat.test(password.value)) {
	        alert('La contrasena debe tener al menos un numero, una letra mayuscula y una letra minuscula con minimo 6');
	        password.focus;
	        return false;
	        console.log(aa);
	    }else{
	    	b = 1;
	    }
	}