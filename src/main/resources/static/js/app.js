//Consulta de inventario con estado activo
function consultarInventarioEstado() {
    consulta("http://localhost:8080/api/v1/fabrica/estado/ACTIVO");
}

//Consulta de todo el inventario
function consultarInventario() {
    consulta("http://localhost:8080/api/v1/fabrica/inventario");
}

//Consultar el inventario defectuoso
function consultarInventarioDefectuoso() {
    consulta("http://localhost:8080/api/v1/fabrica/defectuoso/true");
}

function consulta(uri) {
    $.ajax({
        url: uri,
        type: "GET",
        dataType: "json",
        success: function (response) {
            $("#contenidoInventario").empty();
            console.log(response);
            response.forEach(element => {
                var row = $("<tr>");
                row.append($("<td>").text(element.id));
                row.append($("<td>").text(element.producto));
                row.append($("<td>").text(element.estado));
                row.append($("<td>").text(element.tipo));
                row.append($("<td>").text((element.defectuoso) ? 'SI' : 'NO'));
                row.append($("<td>").text(element.comentario || '-'));
                const aux = element.entrada;
                const entrada = (element.entrada == null)? '' : aux.split('T') || '';
                row.append($("<td>").text(entrada[0] || '-'));
                const aux2 = element.salida || '';
                const salida = aux2.split('T') || '';
                row.append($("<td>").text(salida[0] || '-'));
                row.append($("<td>").text(element.categoria.categoria));
                row.append($("<td>").append('<button type="button" class="icon" onclick="registrarSalida(' + element.id + ')"><img src="img/caja.png" width="30" height="30"></button>'));
                row.append($("<td>").append('<button type="button" class="icon" onclick="registrarDefectuoso(' + element.id + ')"><img src="img/defecto.png" width="30" height="30"></button>'));
                row.append($("<td>").append('<button type="button" class="icon" onclick="eliminarInventario(' + element.id + ')"><img src="img/boton-eliminar.png" width="30" height="30"></button>'));
                $("#contenidoInventario").append(row);
            })
        }
    });
}

//Crear inventario
function crearInventario() {
    const id = $("#id").val();
    const nombre = $("#nombre").val();
    const producto = $("#nombre").val().toUpperCase();
    const estado = $("#estado").val();
    const tipo = $("#tipo").val();
    const aux = document.querySelector('#defectuoso');
    const defectuoso = aux.checked;
    const comentario = $("#comentario").val();
    const entrada = $("#entrada").val();
    const idCategoria = $("#categoria").val();

    console.log(entrada);

    const data = {
        id,
        producto,
        estado,
        tipo,
        defectuoso,
        comentario,
        entrada,
        categoria: {
            id: idCategoria
        }
    };

    $.ajax({
        url: "http://localhost:8080/api/v1/fabrica/inventario/create",
        type: "POST",
        datatype: "json",
        data:JSON.stringify(data),
        headers: {
            "Content-Type": "application/json"
        },
        statusCode: {
            201: function(){
                consultarInventarioEstado();
                $("#id").val("");
                $("#nombre").val("");
                $("#estado").val("ACTIVO");
                document.getElementById("defectuoso").checked = false; 
                $("#comentario").val("");
                $("#entrada").val("0000-00-00");
                $("#categoria").val(101);
            },
            505: function(){
                alert("Ocurrio un error en el consumo");
            }
        }
    });
}

//Cargar categorias
function cargarCategorias() {
    $.ajax({
        url:"http://localhost:8080/api/v1/fabrica/categoria",
        type:"GET",
        datatype:"json",
        success: function (response) {
            $("#categoria").empty();
            //$("#categoria").append($("<option>").val(0).text("Seleccione una categoría"));
            response.forEach(element => {
                var option = $("<option>");
                option.attr("value", element.id);
                const aux = element.categoria;
                const categoria = aux.toLowerCase();
                option.text(categoria);
                $("#categoria").append(option);
            });
        },
        error: function(xhr,status) {
            alert("Ocurrio un error en el consumo.");
        }
    });
}

//Marcar inventario como defectuoso
function registrarDefectuoso(idProducto) {
    actualizar("http://localhost:8080/api/v1/fabrica/update/defectuoso/"+idProducto);
}

//Registrar salida de inventario
function registrarSalida(idProducto) {
    actualizar("http://localhost:8080/api/v1/fabrica/update/salida/"+idProducto);
}

function actualizar(uri) {
    let data = {}

    $.ajax({
        url: uri,
        type: "PUT",
        data: JSON.stringify(data),
        headers: {
            "content-Type": "application/json"
        },
        success:function(response){
            consultarInventarioEstado();
        }
    });
}

//Eliminar producto de inventario
function eliminarInventario(idProducto) {
    let data = {}

    $.ajax({
        url: "http://localhost:8080/api/v1/fabrica/delete/"+idProducto,
        type: "DELETE",
        data: JSON.stringify(data),
        headers: {
            "content-Type": "application/json"
        },
        success:function(response){
            consultarInventarioEstado();
        }
    });
}

//Crear categoria
function crearCategoria() {
    const id = $("#idCat").val();
    const categoria = $("#nombreCat").val().toUpperCase();
    const descripcion = $("#desCat").val();

    const data = {
        id,
        categoria,
        descripcion
    };

    $.ajax({
        url: "http://localhost:8080/api/v1/fabrica/categoria/create",
        type: "POST",
        datatype: "json",
        data:JSON.stringify(data),
        headers: {
            "Content-Type": "application/json"
        },
        statusCode: {
            201: function(){
                $("#idCat").val("");
                $("#nombreCat").val("");
                $("#desCat").val("");
                cargarCategorias();
            },
            505: function(){
                alert("Ocurrio un error en el consumo");
            }
        }
    });
}

//Salir
function salir() {
    window.location = "../login.html";
}

$(document).ready(function(){
    cargarCategorias();
    consultarInventarioEstado();
})

