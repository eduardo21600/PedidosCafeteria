package sv.edu.ues.fia.eisi.pedidoscafeteria;


import android.content.Context;
import android.widget.Toast;

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

public class ControladorServicios {
    //aquí comienzan los erroes de fernando el señor poderoso que obviamente no ha dejado eic115
    RequestQueue requestQueue;
    //aquí los URL de los servicios php para que se vea más ordenado
    String URLBUsu = "";
    String URLEUsu = "";
    String URLCUsu = "";
    String URLAUsu = "";
    String URLBUsus= "";

    String URLBLocal = "";
    String URLELocal = "";
    String URLCLocal = "";
    String URLALocal = "";
    String URLBLocals= "";

    String URLBProducto = "";
    String URLEProducto = "";
    String URLCProducto = "";
    String URLAProducto = "";
    String URLBProductos= "";


    String resultado="";



    private String CrearAct(final Usuario usu, Context context,boolean accion)
    {String url;
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


    private List<Usuario> BuscarUsuarios(Context context)
    {
        final List<Usuario> usus = new ArrayList<>();
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
    private List<Usuario> BuscarUsuario(String IDUSUARIO,Context context)
    {
        final List<Usuario> usus = new ArrayList<>();
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
    private String Eliminar(final Usuario usu, Context context)
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
    private String CrearAct(final Local local, Context context,boolean accion)
    {String url;
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

    private List<Local> BuscarLocales(Context context)
    {
        final List<Local> local = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBUsus, new Response.Listener<JSONArray>() {
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

    private List<Local> BuscarLocal(int IDLOCAL,Context context)
    {
        final List<Local> local = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBUsu+String.valueOf(IDLOCAL), new Response.Listener<JSONArray>() {
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
    private String Eliminar(final Local local, Context context)
    {

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
                parametros.put("IDLOCAL",local.getIdUsuario());

                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;}





    //CRUD Producto
    private String CrearAct(final Producto producto, Context context,boolean accion)
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
                parametros.put("NOMBRELOCAL",String.valueOf(producto.getPrecioUnitario()));
                parametros.put("NOMBRELOCAL",producto.getDescProducto());

                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;}

    private List<Producto> BuscarProductos(Context context)
    {
        final List<Producto> producto = new ArrayList<>();
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

    private List<Producto> BuscarProducto(int IDPRODUCTO,Context context)
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
    private String Eliminar(final Producto producto, Context context)
    {

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
                parametros.put("IDPRODUCTO", String.valueOf(producto.getIdProduto()));

                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;}
}

//aquí terminan los erroes del señor de las tinieblas
