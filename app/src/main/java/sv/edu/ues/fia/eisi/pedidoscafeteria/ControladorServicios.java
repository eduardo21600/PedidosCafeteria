package sv.edu.ues.fia.eisi.pedidoscafeteria;


import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sv.edu.ues.fia.eisi.pedidoscafeteria.callbacks.CallbackRespuestaString;
import sv.edu.ues.fia.eisi.pedidoscafeteria.callbacks.CallbackWS;

public class ControladorServicios{

    public ControladorServicios() {
    }

    CallbackWS callback;
    CallbackRespuestaString callbackRespuestaString;

    public ControladorServicios (CallbackWS callback)
    {
        this.callback = callback;
    }
    public ControladorServicios (CallbackWS callback, CallbackRespuestaString callbackRespuestaString)
    {
        this.callback = callback;
        this.callbackRespuestaString = callbackRespuestaString;
    }

    //aquí comienzan los erroes de fernando el señor poderoso que obviamente no ha dejado eic115
    RequestQueue requestQueue;
    //aquí los URL de los servicios php para que se vea más ordenado
    String URLBUsu = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/usuario_consulta.php?IDUSUARIO=";
    String URLEUsu = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/usuario_eliminar.php";
    String URLCUsu = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/usuario_insertar.php";
    String URLAUsu = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/usuario_actualizar.php";
    String URLBUsus= "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/usuarios_consulta.php";

    String URLBLocal = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/local_consulta.php?IDLOCAL=";
    String URLELocal = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/local_eliminar.php";
    String URLCLocal = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/local_insertar.php";
    String URLALocal = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/local_actualizar.php";
    String URLBLocals= "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/locals_consulta.php";

    String URLBProducto = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/producto_consulta.php?IDPRODUCTO=";
    String URLEProducto = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/producto_eliminar.php";
    String URLCProducto = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/producto_insertar.php";
    String URLAProducto = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/productos_actualizar.php";
    String URLBProductos= "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/productos_consulta.php";

    String URLBTUsu = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/tipousuario_consulta.php?IDTIPOUSUARIO=";
    String URLETUsu = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/tipousuario_eliminar.php";
    String URLCTUsu = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/tipousuario_insertar.php";
    String URLATUsu = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/tipousuario_actualizar.php";
    String URLBTUsus= "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/tipousuarios_consulta.php";

    String URLBMenu = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/menu_consulta.php?IDMENU=";
    String URLBMenuLoc = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/menu_consulta_local.php?IDLOCAL=";
    String URLEMenu = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/menu_eliminar.php";
    String URLCMenu = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/menu_insertar.php";
    String URLAMenu= "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/menu_actualizar.php";
    String URLBMenus= "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/menus_consulta.php";
    String URLCAsigM="https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/asignarP_insertar.php";
    String URLEAsig="https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/asignarP_eliminar.php";
    String URLBAsig="https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/asignarPs_conosulta.php?IDMENU=";

    String URLBDetaPedido = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUes/detalleP_consulta.php?IDDETALLEPEDIDO=";
    String URLEDetaPedido = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUes/detalleP_eliminar.php";
    String URLCDetaPedido = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUes/detalleP_insertar.php";
    String URLADetaPedido= "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUes/detalleP_actualizar.php";
    String URLBDetaPedidos= "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUes/detallePs_consulta.php";

    String URLBEstado = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUes/estadoPe_consulta.php?IDESTADOPEDIDO=";
    String URLEEstado = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUes/estadoPe_eliminar.php";
    String URLCEstado = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUes/estadoPe_insertar.php";
    String URLAEstado= "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUes/estadoPe_actualizar.php";
    String URLBEstados= "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUes/estadoPes_consulta";


    String URLBFacultad = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/ws_facultad_query.php?IDFACULTAD=";
    String URLETFacultad = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/ws_facultad_delete.php";
    String URLCFacultad = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/ws_facultad_insert.php";
    String URLAFacultad = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/ws_facultad_update.php";
    String URLBFacultades = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/ws_facultades_query.php";

    String URLBUbicacion = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/ws_ubicacion_query.php?IDUBICACION=";
    String URLETUbicacion = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/ws_ubicacion_delete.php";
    String URLCUbicacion = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/ws_ubicacion_insert.php";
    String URLAUbicacion = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/ws_ubicacion_update.php";
    String URLBUbicaciones = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/ws_ubicaciones_query.php";

    String URLBPedido = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/ws_pedido_query.php?IDPEDIDO=";
    String URLETPedido = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/ws_pedido_delete.php";
    String URLCPedido = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/ws_pedido_insert.php";
    String URLAPedido = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/ws_pedido_update.php";
    String URLBPedidos = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/ws_pedidos_query.php";
    String URLBPedidosLocal = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/ws_pedidoslocal_query.php?idLocal=";

    String URLBPedidoAsignado = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/ws_pedidoasignado_query.php?IDPEDIDO=?&IDUSUARIO=?&IDPEDIDOASIGNADO=?";
    String URLETPedidoAsignado = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/ws_pedidoasignado_delete.php";
    String URLCPedidoAsignado = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/ws_pedidoasignado_insert.php";
    String URLAPedidoAsignado = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/ws_pedidoasignado_update.php";
    String URLBPedidoAsignados = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/ws_pedidosasignados_query.php";

    String URLBPedidoRealizado = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/ws_pedidorealizado_query.php?IDPEDIDO=?&IDUSUARIO=?&IDPEDIDOREALIZADO=?";
    String URLETPedidoRealizado = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/ws_pedidorealizado_delete.php";
    String URLCPedidoRealizado = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/ws_pedidorealizado_insert.php";
    String URLAPedidoRealizado = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/ws_pedidorealizado_update.php";
    String URLBPedidoRealizados = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/ws_pedidosrealizados_query.php";

    String URLBAccesoUsuario = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/ws_pedidorealizado_query.php?IDUSUARIO=?&ID_OPCION=?&IDACCESOUSUARIO=?";
    String URLETAccesoUsuario = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/ws_accesousuario_delete.php";
    String URLCAccesoUsuario = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/ws_accesousuario_insert.php";
    String URLAAccesoUsuario = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/ws_accesousuario_update.php";
    String URLBAccesoUsuarios = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/ws_accesousuarios_query.php";

    String URLBOpcionCrud = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/ws_opcioncrud_query.php?ID_OPCION=";
    String URLETOpcionCrud = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/ws_opcioncrud_delete.php";
    String URLCOpcionCrud = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/ws_opcioncrud_insert.php";
    String URLAOpcionCrud = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/ws_opcioncrud_update.php";
    String URLBOpcionesCrud = "https://eisi.fia.ues.edu.sv/eisi05/PedidosCafeteriaUES/ws_opcionescrud_query.php";

    String resultado="";



    public String CrearAct(Usuario usuario, Context context,boolean accion)
    {
        final Usuario usu = usuario;
        String url;
        if(accion)
        {
            url = URLCUsu;
        }else {
            url = URLAUsu;
        }
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                resultado ="CONEXIÓN EXITOSA";
                callbackRespuestaString.respuesta(resultado);
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado=error.toString();
                callbackRespuestaString.respuesta(resultado);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros =new HashMap<String,String>();
                parametros.put("IDUSUARIO",usu.getIdUsuario());
                parametros.put("IDTIPOUSUARIO",String.valueOf(usu.getIdTipoUsuario()));
                parametros.put("CONTRASENA",usu.getContrasena());
                parametros.put("NOMBREUSUARIO",usu.getNombreUsuario());
                parametros.put("TELEUSUARIO",usu.getTeleUsuario());
                parametros.put("APELLIDOUSUARIO",usu.getApellidoUsuario());
                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;}


    public List<Usuario> BuscarUsuarios(Context context)
    {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBUsus, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for(int i=0;i<response.length();i++)
                {
                    try {
                        jsonObject = response.getJSONObject(i);
                        usus.add(new Usuario(
                                jsonObject.getString("IDUSUARIO"),
                                jsonObject.getInt("IDTIPOUSUARIO"),
                                jsonObject.getString("CONTRASENA"),
                                jsonObject.getString("NOMBREUSUARIO"),
                                jsonObject.getString("TELEUSUARIO"),
                                jsonObject.getString("APELLIDOUSUARIO")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }

        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado=error.toString();
            }

        });
        requestQueue =Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
        return usus;}

    List<Usuario> usus = new ArrayList<>();

    public List<Usuario> BuscarUsuario(String IDUSUARIO,Context context)
    {
        usus.clear();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBUsu+IDUSUARIO, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for(int i=0;i<response.length();i++)
                {
                    try {
                        jsonObject = response.getJSONObject(i);
                        usus.add(new Usuario(
                                jsonObject.getString("IDUSUARIO"),
                                jsonObject.getInt("IDTIPOUSUARIO"),
                                jsonObject.getString("CONTRASENA"),
                                jsonObject.getString("NOMBREUSUARIO"),
                                jsonObject.getString("TELEUSUARIO"),
                                jsonObject.getString("APELLIDOUSUARIO")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                callback.ResponseWS(usus);
            }

        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado=error.toString();
            }

        });
        requestQueue =Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
        return usus;}
    public String Eliminar(final Usuario usu, Context context)
    {String url;

        StringRequest stringRequest = new StringRequest(Request.Method.POST,URLEUsu, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                resultado ="CONEXIÓN EXITOSA";

            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado=error.toString();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros =new HashMap<String,String>();
                parametros.put("IDUSUARIO",usu.getIdUsuario());

                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;}




    //CRUD LOCAL
    public String CrearAct(Local loc, Context context,boolean accion)
    {
        final Local local=loc;
        String url;
        if(accion)
        {
            url = URLCLocal;
        }else {
            url = URLALocal;
        }
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                resultado ="CONEXIÓN EXITOSA";

            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado=error.toString();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros =new HashMap<String,String>();
                parametros.put("IDLOCAL",String.valueOf(local.getIdLocal()));
                parametros.put("IDUSUARIO",local.getIdUsuario());
                parametros.put("NOMBRELOCAL",local.getNombreLocal());

                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;}

    List<Local> local = new ArrayList<>();

    public List<Local> BuscarLocales(Context context)
    {
        local.clear();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBLocals, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for(int i=0;i<response.length();i++)
                {
                    try {
                        jsonObject = response.getJSONObject(i);
                        local.add(new Local(
                                jsonObject.getInt("IDLOCAL"),
                                jsonObject.getString("IDUSUARIO"),
                                jsonObject.getString("NOMBRELOCAL")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                callback.ResponseWS(local);
            }

        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado=error.toString();
            }

        });
        requestQueue =Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
        return local;}

    public List<Local> BuscarLocal(int IDLOCAL,Context context)
    {
        local.clear();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBLocal+String.valueOf(IDLOCAL), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for(int i=0;i<response.length();i++)
                {
                    try {
                        jsonObject = response.getJSONObject(i);
                        local.add(new Local(
                                jsonObject.getInt("IDLOCAL"),
                                jsonObject.getString("IDUSUARIO"),
                                jsonObject.getString("NOMBRELOCAL")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                callback.ResponseWS(local);
            }

        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado=error.toString();
            }

        });
        requestQueue =Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
        return local;}
    public String Eliminar(final Local local, Context context)
    {

        StringRequest stringRequest = new StringRequest(Request.Method.POST,URLELocal, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                resultado ="CONEXIÓN EXITOSA";

            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado=error.toString();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros =new HashMap<String,String>();
                parametros.put("IDLOCAL",local.getIdUsuario());

                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;}

    //CRUD Producto
    public String CrearAct(final Producto producto, Context context,boolean accion)
    {String url;
        if(accion)
        {
            url = URLCProducto;
        }else {
            url = URLAProducto;
        }
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                resultado ="CONEXIÓN EXITOSA";

            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado=error.toString();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros =new HashMap<String,String>();
                parametros.put("IPRODUCTO",String.valueOf(producto.getIdProduto()));
                parametros.put("NOMBREPRODUCTO",producto.getNombreProducto());
                parametros.put("PRECIOUNITARIO",String.valueOf(producto.getPrecioUnitario()));
                parametros.put("DESCPRODUCTO",producto.getDescProducto());

                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;}

    List<Producto> producto = new ArrayList<>();

    public List<Producto> BuscarProductos(Context context)
    {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBProductos, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for(int i=0;i<response.length();i++)
                {
                    try {
                        jsonObject = response.getJSONObject(i);
                        producto.add(new Producto(
                                jsonObject.getInt("IDPRODUCTO"),
                                jsonObject.getString("NOMBREPRODUCTO"),
                                jsonObject.getInt("PRECIOUNITARIO"),
                                jsonObject.getString("DESCPRODUCTO")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                callback.ResponseWS(producto);
            }

        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado=error.toString();
            }

        });
        requestQueue =Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
        return producto;
    }

    public List<Producto> BuscarProducto(int IDPRODUCTO,Context context)
    {
        final List<Producto> producto = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBProducto+String.valueOf(IDPRODUCTO), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for(int i=0;i<response.length();i++)
                {
                    try {
                        jsonObject = response.getJSONObject(i);
                        producto.add(new Producto(
                                jsonObject.getInt("IDPRODUCTO"),
                                jsonObject.getString("NOMBREPRODUCTO"),
                                jsonObject.getInt("PRECIOUNITARIO"),
                                jsonObject.getString("DESCPRODUCTO")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }

        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado=error.toString();
            }

        });
        requestQueue =Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
        return producto;}
    public String Eliminar(final Producto producto, Context context)
    {

        StringRequest stringRequest = new StringRequest(Request.Method.POST,URLEProducto, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                resultado ="CONEXIÓN EXITOSA";

            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado=error.toString();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros =new HashMap<String,String>();
                parametros.put("IDPRODUCTO", String.valueOf(producto.getIdProduto()));

                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;}



    //CRUD TIPOUSUARIO
    public String CrearAct(final TipoUsuario tipoUsuario, Context context,boolean accion)
    {String url;
        if(accion)
        {
            url = URLCTUsu;
        }else {
            url = URLATUsu;
        }
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                resultado ="CONEXIÓN EXITOSA";

            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado=error.toString();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros =new HashMap<String,String>();
                parametros.put("IDTIPOUSUARIO",String.valueOf(tipoUsuario.getIdTipoUsuario()));
                parametros.put("NOMTIPOUSUARIO",tipoUsuario.getNomTipoUsuario());


                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;}

    public List<TipoUsuario> BuscarTiposU(Context context)
    {
        final List<TipoUsuario> tipoUsuarios = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBTUsus, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for(int i=0;i<response.length();i++)
                {
                    try {
                        jsonObject = response.getJSONObject(i);
                        tipoUsuarios.add(new TipoUsuario(
                                jsonObject.getInt("IDTIPOUSUARIO"),
                                jsonObject.getString("NOMTIPOUSUARIO")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }

        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado=error.toString();
            }

        });
        requestQueue =Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
        return tipoUsuarios;}

    public List<TipoUsuario> BuscarTipoU(int IDTIPOUSUARIO,Context context)
    {
        final List<TipoUsuario> tipoUsuarios = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBTUsu+String.valueOf(IDTIPOUSUARIO), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for(int i=0;i<response.length();i++)
                {
                    try {
                        jsonObject = response.getJSONObject(i);
                        tipoUsuarios.add(new TipoUsuario(
                                jsonObject.getInt("IDTIPOUSUARIO"),
                                jsonObject.getString("NOMTIPOUSUARIO")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }

        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado=error.toString();
            }

        });
        requestQueue =Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
        return tipoUsuarios;}
    public String Eliminar(final TipoUsuario tipoUsuario, Context context)
    {

        StringRequest stringRequest = new StringRequest(Request.Method.POST,URLETUsu, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                resultado ="CONEXIÓN EXITOSA";

            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado=error.toString();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros =new HashMap<String,String>();
                parametros.put("TIPOUSUARIO", String.valueOf(tipoUsuario.getIdTipoUsuario()));

                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;}


    //CRUD producto asignado
    public String Crear(final int IDPRODUCTO, final int IDMENU, Context context)
    {String url;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLCAsigM, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                resultado ="CONEXIÓN EXITOSA";

            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado=error.toString();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros =new HashMap<String,String>();
                parametros.put("IDMENU",String.valueOf(IDMENU));
                parametros.put("IDPRODUCTO", String.valueOf(IDPRODUCTO));


                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;}

    public List<Producto> BuscarAsigM(int IDMENU,Context context)
    {
        final List<Producto> producto = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBAsig+String.valueOf(IDMENU), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for(int i=0;i<response.length();i++)
                {
                    try {
                        jsonObject = response.getJSONObject(i);
                        producto.add(new Producto(
                                jsonObject.getInt("IDPRODUCTO"),
                                jsonObject.getString("NOMBREPRODUCTO"),
                                jsonObject.getInt("PRECIOUNITARIO"),
                                jsonObject.getString("DESCPRODUCTO")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                callback.ResponseWS(producto);
            }

        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado=error.toString();
            }

        });
        requestQueue =Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
        return producto;}
    public String Eliminar(final int IDMENU, Context context)
    {

        StringRequest stringRequest = new StringRequest(Request.Method.POST,URLEAsig, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                resultado ="CONEXIÓN EXITOSA";

            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado=error.toString();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros =new HashMap<String,String>();
                parametros.put("IDMENU", String.valueOf(IDMENU));


                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;}




    //CRUD MENU
    public String CrearAct(final Menu menu, final Context context, boolean accion)
    {String url;

        url = URLCMenu;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                resultado ="CONEXIÓN EXITOSA";

            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado=error.toString();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros =new HashMap<String,String>();
                parametros.put("IDMENU",String.valueOf(menu.getIdMenu()));
                parametros.put("IDLOCAL", String.valueOf(menu.getIdLocal()));
                parametros.put("PRECIOMENU", String.valueOf(menu.getPrecioMenu()));
                parametros.put("FECHADESDEMENU",menu.getFechaDesdeMenu());
                parametros.put("FECHAHASTAMENU",menu.getFechaHastaMenu());
                parametros.put("NOMMENU",menu.getNomMenu());
                for(int i=0;i<menu.getProductos().size();i++)
                {
                    String r = Crear(menu.getIdMenu(),menu.getProductos().get(i).getIdProduto(),context);
                }

                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;}

    public String Act(final Menu menu, final Context context)
    {String url;


        url = URLAMenu;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                resultado ="CONEXIÓN EXITOSA";

            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado=error.toString();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros =new HashMap<String,String>();
                parametros.put("IDMENU",String.valueOf(menu.getIdMenu()));
                parametros.put("IDLOCAL", String.valueOf(menu.getIdLocal()));
                parametros.put("PRECIOMENU", String.valueOf(menu.getPrecioMenu()));
                parametros.put("FECHADESDEMENU",menu.getFechaDesdeMenu());
                parametros.put("FECHAHASTAMENU",menu.getFechaHastaMenu());
                parametros.put("NOMMENU",menu.getNomMenu());
                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;}

    List<Menu> menu = new ArrayList<>();
    public List<Menu> BuscarMenus(final Context context)
    {
        menu.clear();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBMenus, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;


                for(int i=0;i<response.length();i++)
                {
                    try {
                        jsonObject = response.getJSONObject(i);

                        List<Producto> producto = BuscarAsigM(jsonObject.getInt("IDMENU"),context);
                        menu.add(new Menu(
                                jsonObject.getInt("IDMENU"),
                                jsonObject.getInt("IDLOCAL"),
                                jsonObject.getInt("PRECIOMENU"),
                                jsonObject.getString("FECHADESDEMENU"),
                                jsonObject.getString("FECHAHASTAMENU"),
                                jsonObject.getString("NOMMENU")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }

        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado=error.toString();
            }

        });
        requestQueue =Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
        return menu;}

    public List<Menu> BuscarMenu(final int IDMENU, final Context context)
    {
        menu.clear();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBMenu+String.valueOf(IDMENU), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for(int i=0;i<response.length();i++)
                {
                    try {
                        jsonObject = response.getJSONObject(i);
                        List<Producto> producto = BuscarAsigM(IDMENU,context);
                        menu.add(new Menu(
                                jsonObject.getInt("IDMENU"),
                                jsonObject.getInt("IDLOCAL"),
                                jsonObject.getInt("PRECIOMENU"),
                                jsonObject.getString("FECHADESDEMENU"),
                                jsonObject.getString("FECHAHASTAMENU"),
                                jsonObject.getString("NOMMENU")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

            }

        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado=error.toString();
            }

        });
        requestQueue =Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
        return menu;}

    public List<Menu> BuscarMenuLocal(final int IDLOCAL, final Context context)
    {
        menu.clear();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBMenuLoc+String.valueOf(IDLOCAL), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for(int i=0;i<response.length();i++)
                {
                    try {
                        jsonObject = response.getJSONObject(i);
                        //List<Producto> producto = BuscarAsigM(IDLOCAL,context);
                        menu.add(new Menu(
                                jsonObject.getInt("IDMENU"),
                                jsonObject.getInt("IDLOCAL"),
                                jsonObject.getInt("PRECIOMENU"),
                                jsonObject.getString("FECHADESDEMENU"),
                                jsonObject.getString("FECHAHASTAMENU"),
                                jsonObject.getString("NOMMENU")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                callback.ResponseWS(menu);
            }

        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado=error.toString();
            }

        });
        requestQueue =Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
        return menu;
    }

    public String Eliminar(final Menu menu, final Context context)
    {

        StringRequest stringRequest = new StringRequest(Request.Method.POST,URLEMenu, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                resultado ="CONEXIÓN EXITOSA";

            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado=error.toString();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros =new HashMap<String,String>();
                String r = Eliminar(menu.getIdMenu(),context);
                parametros.put("IDMENU", String.valueOf(menu.getIdMenu()));

                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;}




    //CRUD detallePedido
    public String CrearAct(final DetallePedido detallePedido, Context context,boolean accion)
    {String url;
        if(accion)
        {
            url = URLCDetaPedido;
        }else {
            url = URLADetaPedido;
        }
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                resultado ="CONEXIÓN EXITOSA";

            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado=error.toString();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros =new HashMap<String,String>();
                parametros.put("CANTIDAD",String.valueOf(detallePedido.getCantidad()));
                parametros.put("SUBTOTAL", String.valueOf(detallePedido.getSubtotal()));
                parametros.put("IDDETALLEPEDIDO", String.valueOf(detallePedido.getIdDetallePedido()));


                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;}

    public List<DetallePedido> BuscarDetallePedidos(Context context)
    {
        final List<DetallePedido> detallePedidos = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBDetaPedidos, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for(int i=0;i<response.length();i++)
                {
                    try {
                        jsonObject = response.getJSONObject(i);
                        detallePedidos.add(new DetallePedido(
                                jsonObject.getInt("CANTIDAD"),
                                jsonObject.getInt("SUBTUTOAL"),
                                jsonObject.getInt("IDDETALLEPEDIDO"),
                                jsonObject.getInt("IDMENU")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }

        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado=error.toString();
            }

        });
        requestQueue =Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
        return detallePedidos;}

    public List<DetallePedido> BuscarDetallePedido(int IDDETALLEPEDIDO,Context context)
    {
        final List<DetallePedido> detallePedidos= new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBDetaPedido+String.valueOf(IDDETALLEPEDIDO), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for(int i=0;i<response.length();i++)
                {
                    try {
                        jsonObject = response.getJSONObject(i);
                        detallePedidos.add(new DetallePedido(
                                jsonObject.getInt("CANTIDAD"),
                                jsonObject.getInt("SUBTUTOAL"),
                                jsonObject.getInt("IDDETALLEPEDIDO"),
                                jsonObject.getInt("IDMENU")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }

        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado=error.toString();
            }

        });
        requestQueue =Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
        return detallePedidos;}

    public String Eliminar(final DetallePedido detallePedido, Context context)
    {

        StringRequest stringRequest = new StringRequest(Request.Method.POST,URLEDetaPedido, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                resultado ="CONEXIÓN EXITOSA";

            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado=error.toString();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros =new HashMap<String,String>();
                parametros.put("IDDETALLEPEDIDO", String.valueOf(detallePedido.getIdDetallePedido()));

                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;}


    //CRUD estado pedido


    public String CrearAct(final EstadoPedido estadoPedido, Context context,boolean accion)
    {String url;
        if(accion)
        {
            url = URLCEstado;
        }else {
            url = URLAEstado;
        }
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                resultado ="CONEXIÓN EXITOSA";

            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado=error.toString();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros =new HashMap<String,String>();
                parametros.put("IDESTADOPEDIDO",String.valueOf(estadoPedido.getIdEstadoPedido()));
                parametros.put("DESCESTADOPEDIDO",estadoPedido.getDescEstadoPedido());


                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;}

    public List<EstadoPedido> BuscarEstados(Context context)
    {
        final List<EstadoPedido> estadoPedidos = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBEstados, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for(int i=0;i<response.length();i++)
                {
                    try {
                        jsonObject = response.getJSONObject(i);
                        estadoPedidos.add(new EstadoPedido(
                                jsonObject.getInt("IDESTADOPEDIDO"),
                                jsonObject.getString("DESCESTADOPEDIDO")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }

        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado=error.toString();
            }

        });
        requestQueue =Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
        return estadoPedidos;}

    public List<EstadoPedido> BuscarEstadoPedido(int IDESTADOPEDIDO,Context context)
    {
        final List<EstadoPedido> estadoPedidos = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBEstado+String.valueOf(IDESTADOPEDIDO), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for(int i=0;i<response.length();i++)
                {
                    try {
                        jsonObject = response.getJSONObject(i);
                        estadoPedidos.add(new EstadoPedido(
                                jsonObject.getInt("IDESTADOPEDIDO"),
                                jsonObject.getString("DESCESTADOPEDIDO")

                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }

        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado=error.toString();
            }

        });
        requestQueue =Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
        return estadoPedidos;}


    public String Eliminar(final EstadoPedido estadoPedido, Context context)
    {

        StringRequest stringRequest = new StringRequest(Request.Method.POST,URLEEstado, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                resultado ="CONEXIÓN EXITOSA";

            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado=error.toString();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros =new HashMap<String,String>();
                parametros.put("IDESTADOPEDIDO", String.valueOf(estadoPedido.getIdEstadoPedido()));

                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;}
//aquí terminan los erroes del señor de las tinieblas
//CRUD facultad
public String CrearAct(final Facultad facultad, Context context, boolean accion) {
    String url;
    if (accion) {
        url = URLCFacultad;
    } else {
        url = URLAFacultad;
    }
    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {

            resultado = "CONEXIÓN EXITOSA";

        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            resultado = error.toString();

        }
    }) {
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String, String> parametros = new HashMap<String, String>();
            parametros.put("IDFACULTAD", String.valueOf(facultad.getIdFacultad()));
            parametros.put("NOMFACULTAD", facultad.getNomFacultad());

            return parametros;
        }
    };
    requestQueue = Volley.newRequestQueue(context);
    requestQueue.add(stringRequest);
    return resultado;
}
    List<Facultad> facultad = new ArrayList<>();
    public List<Facultad> BuscarFacultades(Context context) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBFacultades, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        facultad.add(new Facultad(
                                jsonObject.getInt(  "IDFACULTAD"),
                                jsonObject.getString("NOMFACULTAD")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                callback.ResponseWS(facultad);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado = error.toString();
            }

        });
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
        return facultad;
    }

    List<Facultad> facultad1 = new ArrayList<>();
    public List<Facultad> BuscarFacultad(int IDFACULTAD, Context context) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBFacultad + String.valueOf(IDFACULTAD), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        facultad1.add(new Facultad(
                                jsonObject.getInt("IDFACULTAD"),
                                jsonObject.getString("NOMFACULTAD")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                callback.ResponseWS(facultad1);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado = error.toString();
            }

        });
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
        return facultad;
    }

    public String Eliminar(final Facultad facultad, Context context) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLETFacultad, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                resultado = "CONEXIÓN EXITOSA";

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado = error.toString();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("IDFACULTAD", String.valueOf(facultad.getIdFacultad()));

                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;
    }

    //CRUD Ubicacion
    public String CrearAct(final Ubicacion ubicacion, Context context, boolean accion) {
        String url;
        if (accion) {
            url = URLCUbicacion;
        } else {
            url = URLAUbicacion;
        }
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                resultado = "CONEXIÓN EXITOSA";

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado = error.toString();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                //parametros.put("IDUBICACION", String.valueOf(ubicacion.getIdUbicacion()));
                parametros.put("IDFACULTAD", String.valueOf(ubicacion.getIdFacultad()));
                //parametros.put("IDPEDIDO", String.valueOf(ubicacion.getIdPedido()));
                parametros.put("DIRECUBICACION", ubicacion.getDirecUbicacion());
                parametros.put("NOMUBICACION", ubicacion.getNomUbicacion());
                parametros.put("PUNTOREFUBICACION", ubicacion.getPuntoRefUbicacion());
                parametros.put("IDUSUARIO", ubicacion.getIdUsuario());
                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;
    }

    //inventos de Pablo no tocar



    List<Ubicacion> ubicacion = new ArrayList<>();
    public List<Ubicacion> BuscarUbicaciones(Context context) {


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBUbicaciones, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        ubicacion.add(new Ubicacion(
                                jsonObject.getInt("IDUBICACION"),
                                jsonObject.getInt("IDFACULTAD"),
                                1,
                                jsonObject.getString("DIRECUBICACION"),
                                jsonObject.getString("NOMUBICACION"),
                                jsonObject.getString("PUNTOREFUBICACION"),
                                    jsonObject.getString("IDUSUARIO")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                callback.ResponseWS(ubicacion);
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado = error.toString();
            }

        });
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
        return ubicacion;
    }

    public List<Ubicacion> BuscarUbicacion(int IDUBICACION, Context context) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBUbicacion + String.valueOf(IDUBICACION), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        ubicacion.add(new Ubicacion(
                                jsonObject.getInt("IDUBICACION"),
                                jsonObject.getInt("IDFACULTAD"),
                                jsonObject.getInt("IDPEDIDO"),
                                jsonObject.getString("DIRECUBICACION"),
                                jsonObject.getString("NOMUBICACION"),
                                jsonObject.getString("PUNTOREFUBICACION"),
                                jsonObject.getString("IDUSUARIO")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                callback.ResponseWS(ubicacion);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado = error.toString();
            }

        });
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
        return ubicacion;
    }

    public String Eliminar(final Ubicacion ubicacion, Context context) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLETUbicacion, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                resultado = "CONEXIÓN EXITOSA";

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado = error.toString();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("IDUBICACION", String.valueOf(ubicacion.getIdUbicacion()));

                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;
    }

    //CRUD PedidoAsignado
    public String CrearAct(final PedidoAsignado pedidoAsignado, Context context, boolean accion) {
        String url;
        if (accion) {
            url = URLCPedidoAsignado;
        } else {
            url = URLAPedidoAsignado;
        }
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                resultado = "CONEXIÓN EXITOSA";

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado = error.toString();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("IDPEDIDO", String.valueOf(pedidoAsignado.getIdPedido()));
                parametros.put("IDUSUARIO", pedidoAsignado.getIdUsuario());
               // parametros.put("IDPEDIDOASIGNADO", String.valueOf(pedidoAsignado.getIdPedidoAsignado()));
                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;
    }

    List<PedidoAsignado> pedidoAsignados = new ArrayList<>();
    public List<PedidoAsignado> BuscarPedidosAsignados(Context context) {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBPedidoAsignados, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        pedidoAsignados.add(new PedidoAsignado(
                                jsonObject.getInt("IDPEDIDO"),
                                jsonObject.getString("IDUSUARIO")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                callback.ResponseWS(pedidoAsignados);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado = error.toString();
            }

        });
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
        return pedidoAsignados;
    }

    List<PedidoAsignado> pedidoAsignado = new ArrayList<>();
    public List<PedidoAsignado> BuscarPedidoAsignado(int IDPEDIDO, String IDUSUARIO, Context context) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBPedidoAsignado + String.valueOf(IDPEDIDO) + IDUSUARIO, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        pedidoAsignado.add(new PedidoAsignado(
                                jsonObject.getInt("IDPEDIDO"),
                                jsonObject.getString("IDUSUARIO")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                callback.ResponseWS(pedidoAsignado);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado = error.toString();
            }

        });
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
        return pedidoAsignados;
    }

    public String Eliminar(final PedidoAsignado pedidoAsignado, Context context) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLETPedidoAsignado, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                resultado = "CONEXIÓN EXITOSA";

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado = error.toString();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("IDPEDIDO", String.valueOf(pedidoAsignado.getIdPedido()));
                parametros.put("IDUSUARIO", pedidoAsignado.getIdUsuario());
               // parametros.put("IDPEDIDOASIGNADO", String.valueOf(pedidoAsignado.getIdPedidoAsignado()));

                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;
    }
    //CRUD PedidoRealizado
    public String CrearAct(final PedidoRealizado pedidoRealizado, Context context, boolean accion) {
        String url;
        if (accion) {
            url = URLCPedidoRealizado;
        } else {
            url = URLAPedidoRealizado;
        }
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                resultado = "CONEXIÓN EXITOSA";

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado = error.toString();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("IDPEDIDO", String.valueOf(pedidoRealizado.getIdPedido()));
                parametros.put("IDUSUARIO", pedidoRealizado.getIdUsuario());
                //parametros.put("IDPEDIDOASIGNADO", String.valueOf(pedidoRealizado.getIdPedidoRealizado()));
                parametros.put("TIPOPEDREALIZADO", pedidoRealizado.getTipo());
                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;
    }

    List<PedidoRealizado> pedidoRealizados = new ArrayList<>();
    public List<PedidoRealizado> BuscarPedidosRealizados(Context context) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBPedidoRealizados, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        pedidoRealizados.add(new PedidoRealizado(
                                jsonObject.getInt("IDPEDIDO"),
                                jsonObject.getString("IDUSUARIO"),
//                                jsonObject.getInt("IDPEDIDOREALIZADO"),
                                jsonObject.getString("TIPOPEDREALIZADO")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                callback.ResponseWS(pedidoRealizados);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado = error.toString();
            }

        });
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
        return pedidoRealizados;
    }

    List<PedidoRealizado> pedidoRealizado = new ArrayList<>();
    public List<PedidoRealizado> BuscarPedidoRealizado(int IDPEDIDO, String IDUSUARIO, Context context) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBPedidoRealizado + String.valueOf(IDPEDIDO) + IDUSUARIO, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        pedidoRealizado.add(new PedidoRealizado(
                                jsonObject.getInt("IDPEDIDO"),
                                jsonObject.getString("IDUSUARIO"),
                                //jsonObject.getInt("IDPEDIDOREALIZADO"),
                                jsonObject.getString("TIPOPEDREALIZADO")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                callback.ResponseWS(pedidoRealizado);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado = error.toString();
            }

        });
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
        return pedidoRealizados;
    }

    public String Eliminar(final PedidoRealizado pedidoRealizado, Context context) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLETPedidoRealizado, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                resultado = "CONEXIÓN EXITOSA";

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado = error.toString();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("IDPEDIDO", String.valueOf(pedidoRealizado.getIdPedido()));
                parametros.put("IDUSUARIO", pedidoRealizado.getIdUsuario());
                //parametros.put("IDPEDIDOREALIZADO", String.valueOf(pedidoRealizado.getIdPedidoRealizado()));
                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;
    }

    //CRUD AccesoUsuario
    public String CrearAct(final AccesoUsuario accesoUsuario, Context context, boolean accion) {
        String url;
        if (accion) {
            url = URLCAccesoUsuario;
        } else {
            url = URLAAccesoUsuario;
        }
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                resultado = "CONEXIÓN EXITOSA";

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado = error.toString();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("IDUSUARIO", accesoUsuario.getIdUsuario());
                parametros.put("ID_OPCION",accesoUsuario.getIdOpcion());
               // parametros.put("IDACCESOUSUARIO", String.valueOf(accesoUsuario.getIdAccesoUsuario()));
                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;
    }

    List<AccesoUsuario> accesoUsuarios = new ArrayList<>();
    public List<AccesoUsuario> BuscarAccesoUsuarios(Context context) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBAccesoUsuarios, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        accesoUsuarios.add(new AccesoUsuario(
                                jsonObject.getString("IDUSUARIO"),
                                jsonObject.getString("IDOPCION")
                                //jsonObject.getInt("IDACCESOUSUARIO")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                callback.ResponseWS(accesoUsuarios);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado = error.toString();
            }

        });
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
        return accesoUsuarios;
    }

    List<AccesoUsuario> accesoUsuario = new ArrayList<>();
    public List<AccesoUsuario> BuscarAccesoUsuario(String IDUSUARIO, String IDOPCION, Context context) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBAccesoUsuario + IDUSUARIO + IDOPCION, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        accesoUsuario.add(new AccesoUsuario(
                                jsonObject.getString("IDUSUARIO"),
                                jsonObject.getString("IDOPCION")
                              //  jsonObject.getInt("IDACCESOUSUARIO")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                callback.ResponseWS(accesoUsuario);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado = error.toString();
            }

        });
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
        return accesoUsuarios;
    }

    public String Eliminar(final AccesoUsuario accesoUsuario, Context context) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLETAccesoUsuario, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                resultado = "CONEXIÓN EXITOSA";

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado = error.toString();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("IDUSUARIO", accesoUsuario.getIdUsuario());
                parametros.put("ID_OPCION", accesoUsuario.getIdOpcion());
              //  parametros.put("IDACCESOUSUARIO", String.valueOf(accesoUsuario.getIdAccesoUsuario()));
                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;
    }

    //CRUD OpcionCrud
    public String CrearAct(final OpcionCrud opcionCrud, Context context, boolean accion) {
        String url;
        if (accion) {
            url = URLCOpcionCrud;
        } else {
            url = URLAOpcionCrud;
        }
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                resultado = "CONEXIÓN EXITOSA";

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado = error.toString();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("ID_OPCION", opcionCrud.getIdOpcion());
                parametros.put("DESOPCION",opcionCrud.getDescOpcion());
                parametros.put("NUMCRUD", String.valueOf(opcionCrud.getNumCrud()));
                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;
    }

    List<OpcionCrud> opcionesCrud = new ArrayList<>();
    public List<OpcionCrud> BuscarOpcionesCrud(Context context) {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBOpcionesCrud, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        opcionesCrud.add(new OpcionCrud(
                                jsonObject.getString("IDOPCION"),
                                jsonObject.getString("DESOPCION"),
                                jsonObject.getInt("NUMCRUD")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                callback.ResponseWS(opcionesCrud);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado = error.toString();
            }

        });
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
        return opcionesCrud;
    }

    List<OpcionCrud> opcionCrud = new ArrayList<>();
    public List<OpcionCrud> BuscarOpcionCrud(String IDOPCION, Context context) {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBOpcionCrud + IDOPCION, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        opcionCrud.add(new OpcionCrud(
                                jsonObject.getString("IDOPCION"),
                                jsonObject.getString("DESOPCION"),
                                jsonObject.getInt("NUMCRUD")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                callback.ResponseWS(opcionCrud);
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado = error.toString();
            }

        });
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
        return opcionCrud;
    }

    public String Eliminar(final OpcionCrud opcionCrud, Context context) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLETOpcionCrud, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                resultado = "CONEXIÓN EXITOSA";

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado = error.toString();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("ID_OPCION", opcionCrud.getIdOpcion());
                parametros.put("DESOPCION", opcionCrud.getDescOpcion());
                parametros.put("NUMCRUD", String.valueOf(opcionCrud.getNumCrud()));
                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;
    }

    //CRUD pedido
    public String CrearAct(final Pedido pedido, final Context context, final boolean accion)
    {String url;

        url = URLCPedido;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                resultado ="CONEXIÓN EXITOSA";

            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado=error.toString();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros =new HashMap<String,String>();
                parametros.put("IDPEDIDO",String.valueOf(pedido.getIdPedido()));
                parametros.put("IDESTADOPEDIDO", String.valueOf(pedido.getIdEstadoPedido()));
                parametros.put("IDLOCAL", String.valueOf(pedido.getIdLocal()));
                parametros.put("IDUBICACION",String.valueOf(pedido.getIdUbicacion()));
                parametros.put("FECHAPEDIDO",pedido.getFechaPedido());
                parametros.put("TOTALPEDIDO",String.valueOf(pedido.getTotalPedido()));

                for(int i=0;i<pedido.getDetallePedidos().size();i++)
                {
                    DetallePedido detallePedido = new DetallePedido();
                    detallePedido.setIdDetallePedido(pedido.getDetallePedidos().get(i).getIdDetallePedido());
                    detallePedido.setCantidad(pedido.getDetallePedidos().get(i).getCantidad());
                    detallePedido.setSubtotal(pedido.getDetallePedidos().get(i).getSubtotal());
                    String det = CrearAct(detallePedido,context,accion);
                }

                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;}

    public String Act(final Pedido pedido, final Context context)
    {
        String url;

        url = URLAPedido;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                resultado ="CONEXIÓN EXITOSA";

            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado=error.toString();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros =new HashMap<String,String>();
                parametros.put("IDPEDIDO",String.valueOf(pedido.getIdPedido()));
                parametros.put("IDESTADOPEDIDO", String.valueOf(pedido.getIdEstadoPedido()));
                parametros.put("IDLOCAL", String.valueOf(pedido.getIdLocal()));
                parametros.put("IDUBICACION",String.valueOf(pedido.getIdUbicacion()));
                parametros.put("FECHAPEDIDO",pedido.getFechaPedido());
                parametros.put("TOTALPEDIDO",String.valueOf(pedido.getTotalPedido()));
                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;}

    List<Pedido> pedido = new ArrayList<>();
    public List<Pedido> BuscarPedidos(final Context context)
    {
        final List<DetallePedido> detallePedidos = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBPedidos, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for(int i=0;i<response.length();i++)
                {
                    try {
                        jsonObject = response.getJSONObject(i);

                        for(int j = 0; j < pedido.get(i).getDetallePedidos().size(); j++)
                        {
                            detallePedidos.add((DetallePedido) BuscarDetallePedido(jsonObject.getInt("IDDETALLEPEDIDO"), context));
                        }

                        pedido.add(new Pedido(
                                jsonObject.getInt("IDPEDIDO"),
                                (ArrayList<DetallePedido>) detallePedidos,
                                jsonObject.getInt("IDESTADOPEDIDO"),
                                jsonObject.getInt("IDLOCAL"),
                                jsonObject.getInt("IDUBICACION"),
                                jsonObject.getString("FECHAPEDIDO"),
                                jsonObject.getDouble("TOTALPEDIDO")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                callback.ResponseWS(pedido);
            }

        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado=error.toString();
            }

        });
        requestQueue =Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
        return pedido;}

    List<Pedido> pedido2 = new ArrayList<>();
    public List<Pedido> BuscarPedidosLocal(int idLocal, final Context context)
    {
        final List<DetallePedido> detallePedidos = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBPedidosLocal + String.valueOf(idLocal), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for(int i=0;i<response.length();i++)
                {
                    try {
                        jsonObject = response.getJSONObject(i);

                        for(int j = 0; j < pedido.get(i).getDetallePedidos().size(); j++)
                        {
                            detallePedidos.add((DetallePedido) BuscarDetallePedido(jsonObject.getInt("IDDETALLEPEDIDO"), context));
                        }
                        pedido2.add(new Pedido(
                                jsonObject.getInt("IDPEDIDO"),
                                (ArrayList<DetallePedido>) detallePedidos,
                                jsonObject.getInt("IDESTADOPEDIDO"),
                                jsonObject.getInt("IDLOCAL"),
                                jsonObject.getInt("IDUBICACION"),
                                jsonObject.getString("FECHAPEDIDO"),
                                jsonObject.getDouble("TOTALPEDIDO")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                callback.ResponseWS(pedido2);
            }

        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado=error.toString();
            }

        });
        requestQueue =Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
        return pedido;}

    List<Pedido> pedidos = new ArrayList<>();
    public List<Pedido> BuscarPedido(int IDPEDIDO, final Context context)
    {
        final List<DetallePedido> detallePedidos = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBPedido+String.valueOf(IDPEDIDO), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for(int i=0;i<response.length();i++)
                {
                    try {
                        jsonObject = response.getJSONObject(i);

                        for(int j = 0; j < pedidos.get(i).getDetallePedidos().size(); j++)
                        {
                            detallePedidos.add((DetallePedido) BuscarDetallePedido(jsonObject.getInt("IDDETALLEPEDIDO"), context));
                        }

                        pedidos.add(new Pedido(
                                jsonObject.getInt("IDPEDIDO"),
                                (ArrayList<DetallePedido>) detallePedidos,
                                jsonObject.getInt("IDESTADOPEDIDO"),
                                jsonObject.getInt("IDLOCAL"),
                                jsonObject.getInt("IDUBICACION"),
                                jsonObject.getString("FECHAPEDIDO"),
                                jsonObject.getDouble("TOTALPEDIDO")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                callback.ResponseWS(pedidos);
            }

        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado=error.toString();
            }

        });
        requestQueue =Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
        return pedidos;
    }
    public String Eliminar(final Pedido pedido, final Context context)
    {

        StringRequest stringRequest = new StringRequest(Request.Method.POST,URLETPedido, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                resultado ="CONEXIÓN EXITOSA";

            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado=error.toString();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros =new HashMap<String,String>();
                parametros.put("IDPEDIDO", String.valueOf(pedido.getIdPedido()));
                List<DetallePedido> detallePedidos = pedido.getDetallePedidos();
                for (int i = 0; i < pedido.getDetallePedidos().size();i++)
                {
                    Eliminar(detallePedidos.get(i),context);
                }

                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;
    }


}


