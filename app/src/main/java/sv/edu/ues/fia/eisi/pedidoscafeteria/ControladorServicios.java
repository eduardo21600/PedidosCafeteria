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

import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sv.edu.ues.fia.eisi.pedidoscafeteria.ui.AsignarProducto;

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

    String URLBTUsu = "";
    String URLETUsu = "";
    String URLCTUsu = "";
    String URLATUsu = "";
    String URLBUTsus= "";

    String URLBMenu = "";
    String URLETMenu = "";
    String URLCMenu = "";
    String URLAMenu= "";
    String URLBUMenu= "";
    String URLCAsigM="";
    String URLDAsig="";
    String URLBAsig="";

    String URLBDetaPedido = "";
    String URLETDetaPedido = "";
    String URLCDetaPedido = "";
    String URLADetaPedido= "";
    String URLBUDetaPedido= "";

    String URLBEstado = "";
    String URLETEstado = "";
    String URLCEstado = "";
    String URLAEstado= "";
    String URLBUEstado= "";



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



    //CRUD TIPOUSUARIO
    private String CrearAct(final TipoUsuario tipoUsuario, Context context,boolean accion)
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

    private List<TipoUsuario> BuscarTiposU(Context context)
    {
        final List<TipoUsuario> tipoUsuarios = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBUTsus, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for(int i=0;i<response.length();i++)
                {
                    try {
                        jsonObject = response.getJSONObject(i);
                        tipoUsuarios.add(new TipoUsuario(
                                jsonObject.getInt("IDTIPOUSUARIO"),
                                jsonObject.getString("IDNOMSUARIO")
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

    private List<TipoUsuario> BuscarTipoU(int IDTIPOUSUARIO,Context context)
    {
        final List<TipoUsuario> tipoUsuarios = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBUsu+String.valueOf(IDTIPOUSUARIO), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for(int i=0;i<response.length();i++)
                {
                    try {
                        jsonObject = response.getJSONObject(i);
                        tipoUsuarios.add(new TipoUsuario(
                                jsonObject.getInt("IDTIPOUSUARIO"),
                                jsonObject.getString("IDNOMSUARIO")
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
    private String Eliminar(final TipoUsuario tipoUsuario, Context context)
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
                parametros.put("TIPOUSUARIO", String.valueOf(tipoUsuario.getIdTipoUsuario()));

                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;}




    //CRUD producto asignado
    private String Crear(final int IDPRODUCTO, final int IDMENU, Context context)
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



    private List<Producto> BuscarAsigM(int IDMENU,Context context)
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
    private String Eliminar(final int IDMENU, Context context)
    {

        StringRequest stringRequest = new StringRequest(Request.Method.POST,URLETMenu, new Response.Listener<String>() {
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
    private String CrearAct(final Menu menu, final Context context, boolean accion)
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
                parametros.put("NOMBRELOCAL", String.valueOf(menu.getPrecioMenu()));
                parametros.put("FECHADESDEMENU",menu.getFechaDesdeMenu());
                parametros.put("NOMBRELOCAL",menu.getFechaHastaMenu());
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

    private String Act(final Menu menu, final Context context)
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
                parametros.put("NOMBRELOCAL", String.valueOf(menu.getPrecioMenu()));
                parametros.put("FECHADESDEMENU",menu.getFechaDesdeMenu());
                parametros.put("NOMBRELOCAL",menu.getFechaHastaMenu());
                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;}



    private List<Menu> BuscarMenus(final Context context)
    {
        final List<Menu> menu = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBUMenu, new Response.Listener<JSONArray>() {
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
                                producto,
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

    private List<Menu> BuscarMenu(int IDMENU, final Context context)
    {
        final List<Menu> menu = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBMenu+String.valueOf(IDMENU), new Response.Listener<JSONArray>() {
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
                                producto,
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
    private String Eliminar(final Menu menu, final Context context)
    {

        StringRequest stringRequest = new StringRequest(Request.Method.POST,URLETMenu, new Response.Listener<String>() {
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
                parametros.put("IDLOCAL", String.valueOf(menu.getIdMenu()));

                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;}





    private String CrearAct(final DetallePedido detallePedido, Context context,boolean accion)
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
                parametros.put("IDCANTIDAD",String.valueOf(detallePedido.getCantidad()));
                parametros.put("subtotal", String.valueOf(detallePedido.getSubtotal()));
                parametros.put("IDDETALLEPEDIDO", String.valueOf(detallePedido.getIdDetallePedido()));


                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;}

    private List<DetallePedido> BuscarDetallePedido(Context context)
    {
        final List<DetallePedido> detallePedidos = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBUsus, new Response.Listener<JSONArray>() {
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
                                jsonObject.getInt("IDDETALLEPEDIDO")
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

    private List<DetallePedido> BuscarDetallePedido(int IDDETALLEPEDIDO,Context context)
    {
        final List<DetallePedido> detallePedidos= new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBUsu+String.valueOf(IDDETALLEPEDIDO), new Response.Listener<JSONArray>() {
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
                                jsonObject.getInt("IDDETALLEPEDIDO")
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

    private String Eliminar(final DetallePedido detallePedido, Context context)
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
                parametros.put("IDDETALLEPEDIDO", String.valueOf(detallePedido.getIdDetallePedido()));

                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;}


    private String CrearAct(final EstadoPedido estadoPedido, Context context,boolean accion)
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

    private List<EstadoPedido> BuscarEstados(Context context)
    {
        final List<EstadoPedido> estadoPedidos = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBUsus, new Response.Listener<JSONArray>() {
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

    private List<EstadoPedido> BuscarEstadoPedido(int IDESTADOPEDIDO,Context context)
    {
        final List<EstadoPedido> estadoPedidos = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBUsu+String.valueOf(IDESTADOPEDIDO), new Response.Listener<JSONArray>() {
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


    private String Eliminar(final EstadoPedido estadoPedido, Context context)
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
                parametros.put("IDESTADOPEDIDO", String.valueOf(estadoPedido.getIdEstadoPedido()));

                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;}







}

//aquí terminan los erroes del señor de las tinieblas
