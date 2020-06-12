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
    String URLBUsus = "";

    String URLBLocal = "";
    String URLELocal = "";
    String URLCLocal = "";
    String URLALocal = "";
    String URLBLocals = "";

    String URLBProducto = "";
    String URLEProducto = "";
    String URLCProducto = "";
    String URLAProducto = "";
    String URLBProductos = "";

    String URLBTUsu = "";
    String URLETUsu = "";
    String URLCTUsu = "";
    String URLATUsu = "";
    String URLBUTsus = "";

    String URLBMenu = "";
    String URLETMenu = "";
    String URLCMenu = "";
    String URLAMenu = "";
    String URLBUMenu = "";
    String URLCAsigM = "";
    String URLDAsig = "";
    String URLBAsig = "";

    String URLBDetaPedido = "";
    String URLETDetaPedido = "";
    String URLCDetaPedido = "";
    String URLADetaPedido = "";
    String URLBUDetaPedido = "";

    String URLBEstado = "";
    String URLETEstado = "";
    String URLCEstado = "";
    String URLAEstado = "";
    String URLBUEstado = "";

    String URLBFacultad = "";
    String URLETFacultad = "";
    String URLCFacultad = "";
    String URLAFacultad = "";
    String URLBFacultades = "";

    String URLBUbicacion = "";
    String URLETUbicacion = "";
    String URLCUbicacion = "";
    String URLAUbicacion = "";
    String URLBUbicaciones = "";

    String URLBPedido = "";
    String URLETPedido = "";
    String URLCPedido = "";
    String URLAPedido = "";
    String URLBPedidos = "";

    String URLBPedidoAsignado = "";
    String URLETPedidoAsignado = "";
    String URLCPedidoAsignado = "";
    String URLAPedidoAsignado = "";
    String URLBPedidoAsignados = "";

    String URLBPedidoRealizado = "";
    String URLETPedidoRealizado = "";
    String URLCPedidoRealizado = "";
    String URLAPedidoRealizado = "";
    String URLBPedidoRealizados = "";

    String URLBAccesoUsuario = "";
    String URLETAccesoUsuario = "";
    String URLCAccesoUsuario = "";
    String URLAAccesoUsuario = "";
    String URLBAccesoUsuarios = "";

    String URLBOpcionCrud = "";
    String URLETOpcionCrud = "";
    String URLCOpcionCrud = "";
    String URLAOpcionCrud = "";
    String URLBOpcionesCrud = "";

    String resultado = "";

    private String CrearAct(final Usuario usu, Context context, boolean accion) {
        String url;
        if (accion) {
            url = URLCUsu;
        } else {
            url = URLAUsu;
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
                parametros.put("IDUSUARIO", usu.getIdUsuario());
                parametros.put("IDTIPOUSUARIO", String.valueOf(usu.getIdTipoUsuario()));
                parametros.put("CONTRASENA", usu.getContrasena());
                parametros.put("NOMBREUSUARIO", usu.getNombreUsuario());
                parametros.put("TELEUSUARIO", usu.getTeleUsuario());
                parametros.put("APELLIDOUSUARIO", usu.getApellidoUsuario());
                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;
    }


    private List<Usuario> BuscarUsuarios(Context context) {
        final List<Usuario> usus = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBUsus, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
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

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado = error.toString();
            }

        });
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
        return usus;
    }

    private List<Usuario> BuscarUsuario(String IDUSUARIO, Context context) {
        final List<Usuario> usus = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBUsu + IDUSUARIO, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
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

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado = error.toString();
            }

        });
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
        return usus;
    }

    private String Eliminar(final Usuario usu, Context context) {
        String url;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLEUsu, new Response.Listener<String>() {
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
                parametros.put("IDUSUARIO", usu.getIdUsuario());

                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;
    }


    //CRUD LOCAL
    private String CrearAct(final Local local, Context context, boolean accion) {
        String url;
        if (accion) {
            url = URLCLocal;
        } else {
            url = URLALocal;
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
                parametros.put("IDLOCAL", String.valueOf(local.getIdLocal()));
                parametros.put("IDUSUARIO", local.getIdUsuario());
                parametros.put("NOMBRELOCAL", local.getNombreLocal());

                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;
    }

    private List<Local> BuscarLocales(Context context) {
        final List<Local> local = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBUsus, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
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

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado = error.toString();
            }

        });
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
        return local;
    }

    private List<Local> BuscarLocal(int IDLOCAL, Context context) {
        final List<Local> local = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBUsu + String.valueOf(IDLOCAL), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
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

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado = error.toString();
            }

        });
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
        return local;
    }

    private String Eliminar(final Local local, Context context) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLEUsu, new Response.Listener<String>() {
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
                parametros.put("IDLOCAL", local.getIdUsuario());

                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;
    }


    //CRUD Producto
    private String CrearAct(final Producto producto, Context context, boolean accion) {
        String url;
        if (accion) {
            url = URLCProducto;
        } else {
            url = URLAProducto;
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
                parametros.put("IPRODUCTO", String.valueOf(producto.getIdProduto()));
                parametros.put("NOMBREPRODUCTO", producto.getNombreProducto());
                parametros.put("NOMBRELOCAL", String.valueOf(producto.getPrecioUnitario()));
                parametros.put("NOMBRELOCAL", producto.getDescProducto());

                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;
    }

    private List<Producto> BuscarProductos(Context context) {
        final List<Producto> producto = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBProductos, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
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

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado = error.toString();
            }

        });
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
        return producto;
    }

    private List<Producto> BuscarProducto(int IDPRODUCTO, Context context) {
        final List<Producto> producto = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBProducto + String.valueOf(IDPRODUCTO), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
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

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado = error.toString();
            }

        });
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
        return producto;
    }

    private String Eliminar(final Producto producto, Context context) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLEUsu, new Response.Listener<String>() {
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
                parametros.put("IDPRODUCTO", String.valueOf(producto.getIdProduto()));

                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;
    }


    //CRUD TIPOUSUARIO
    private String CrearAct(final TipoUsuario tipoUsuario, Context context, boolean accion) {
        String url;
        if (accion) {
            url = URLCTUsu;
        } else {
            url = URLATUsu;
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
                parametros.put("IDTIPOUSUARIO", String.valueOf(tipoUsuario.getIdTipoUsuario()));
                parametros.put("NOMTIPOUSUARIO", tipoUsuario.getNomTipoUsuario());


                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;
    }

    private List<TipoUsuario> BuscarTiposU(Context context) {
        final List<TipoUsuario> tipoUsuarios = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBUTsus, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
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

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado = error.toString();
            }

        });
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
        return tipoUsuarios;
    }

    private List<TipoUsuario> BuscarTipoU(int IDTIPOUSUARIO, Context context) {
        final List<TipoUsuario> tipoUsuarios = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBUsu + String.valueOf(IDTIPOUSUARIO), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
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

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado = error.toString();
            }

        });
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
        return tipoUsuarios;
    }

    private String Eliminar(final TipoUsuario tipoUsuario, Context context) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLEUsu, new Response.Listener<String>() {
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
                parametros.put("TIPOUSUARIO", String.valueOf(tipoUsuario.getIdTipoUsuario()));

                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;
    }


    //CRUD producto asignado
    private String Crear(final int IDPRODUCTO, final int IDMENU, Context context) {
        String url;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLCAsigM, new Response.Listener<String>() {
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
                parametros.put("IDMENU", String.valueOf(IDMENU));
                parametros.put("IDPRODUCTO", String.valueOf(IDPRODUCTO));


                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;
    }


    private List<Producto> BuscarAsigM(int IDMENU, Context context) {
        final List<Producto> producto = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBAsig + String.valueOf(IDMENU), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
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

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado = error.toString();
            }

        });
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
        return producto;
    }

    private String Eliminar(final int IDMENU, Context context) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLETMenu, new Response.Listener<String>() {
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
                parametros.put("IDMENU", String.valueOf(IDMENU));


                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;
    }


    //CRUD MENU
    private String CrearAct(final Menu menu, final Context context, boolean accion) {
        String url;

        url = URLCMenu;

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
                parametros.put("IDMENU", String.valueOf(menu.getIdMenu()));
                parametros.put("IDLOCAL", String.valueOf(menu.getIdLocal()));
                parametros.put("NOMBRELOCAL", String.valueOf(menu.getPrecioMenu()));
                parametros.put("FECHADESDEMENU", menu.getFechaDesdeMenu());
                parametros.put("NOMBRELOCAL", menu.getFechaHastaMenu());
                for (int i = 0; i < menu.getProductos().size(); i++) {
                    String r = Crear(menu.getIdMenu(), menu.getProductos().get(i).getIdProduto(), context);
                }

                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;
    }

    private String Act(final Menu menu, final Context context) {
        String url;


        url = URLAMenu;

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
                parametros.put("IDMENU", String.valueOf(menu.getIdMenu()));
                parametros.put("IDLOCAL", String.valueOf(menu.getIdLocal()));
                parametros.put("NOMBRELOCAL", String.valueOf(menu.getPrecioMenu()));
                parametros.put("FECHADESDEMENU", menu.getFechaDesdeMenu());
                parametros.put("NOMBRELOCAL", menu.getFechaHastaMenu());
                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;
    }


    private List<Menu> BuscarMenus(final Context context) {
        final List<Menu> menu = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBUMenu, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;


                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);

                        List<Producto> producto = BuscarAsigM(jsonObject.getInt("IDMENU"), context);
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

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado = error.toString();
            }

        });
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
        return menu;
    }

    private List<Menu> BuscarMenu(int IDMENU, final Context context) {
        final List<Menu> menu = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBMenu + String.valueOf(IDMENU), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        List<Producto> producto = BuscarAsigM(jsonObject.getInt("IDMENU"), context);
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

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado = error.toString();
            }

        });
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
        return menu;
    }

    private String Eliminar(final Menu menu, final Context context) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLETMenu, new Response.Listener<String>() {
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
                String r = Eliminar(menu.getIdMenu(), context);
                parametros.put("IDLOCAL", String.valueOf(menu.getIdMenu()));

                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;
    }


    private String CrearAct(final DetallePedido detallePedido, Context context, boolean accion) {
        String url;
        if (accion) {
            url = URLCDetaPedido;
        } else {
            url = URLADetaPedido;
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
                parametros.put("IDCANTIDAD", String.valueOf(detallePedido.getCantidad()));
                parametros.put("subtotal", String.valueOf(detallePedido.getSubtotal()));
                parametros.put("IDDETALLEPEDIDO", String.valueOf(detallePedido.getIdDetallePedido()));


                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;
    }

    private List<DetallePedido> BuscarDetallePedido(Context context) {
        final List<DetallePedido> detallePedidos = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBUsus, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
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

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado = error.toString();
            }

        });
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
        return detallePedidos;
    }

    private List<DetallePedido> BuscarDetallePedido(int IDDETALLEPEDIDO, Context context) {
        final List<DetallePedido> detallePedidos = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBUsu + String.valueOf(IDDETALLEPEDIDO), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
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

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado = error.toString();
            }

        });
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
        return detallePedidos;
    }

    private String Eliminar(final DetallePedido detallePedido, Context context) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLEUsu, new Response.Listener<String>() {
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
                parametros.put("IDDETALLEPEDIDO", String.valueOf(detallePedido.getIdDetallePedido()));

                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;
    }


    private String CrearAct(final EstadoPedido estadoPedido, Context context, boolean accion) {
        String url;
        if (accion) {
            url = URLCEstado;
        } else {
            url = URLAEstado;
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
                parametros.put("IDESTADOPEDIDO", String.valueOf(estadoPedido.getIdEstadoPedido()));
                parametros.put("DESCESTADOPEDIDO", estadoPedido.getDescEstadoPedido());


                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;
    }

    private List<EstadoPedido> BuscarEstados(Context context) {
        final List<EstadoPedido> estadoPedidos = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBUsus, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
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

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado = error.toString();
            }

        });
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
        return estadoPedidos;
    }

    private List<EstadoPedido> BuscarEstadoPedido(int IDESTADOPEDIDO, Context context) {
        final List<EstadoPedido> estadoPedidos = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBUsu + String.valueOf(IDESTADOPEDIDO), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
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

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                resultado = error.toString();
            }

        });
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
        return estadoPedidos;
    }


    private String Eliminar(final EstadoPedido estadoPedido, Context context) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLEUsu, new Response.Listener<String>() {
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
                parametros.put("IDESTADOPEDIDO", String.valueOf(estadoPedido.getIdEstadoPedido()));

                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;
    }

//aquí terminan los erroes del señor de las tinieblas

    //CRUD facultad
    private String CrearAct(final Facultad facultad, Context context, boolean accion) {
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
                parametros.put("IDFACULTAD", facultad.getIdFacultad());
                parametros.put("NOMFACULTAD", facultad.getNomFacultad());

                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;
    }

    private List<Facultad> BuscarFacultades(Context context) {
        final List<Facultad> facultad = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBFacultades, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        facultad.add(new Facultad(
                                jsonObject.getString("IDFACULTAD"),
                                jsonObject.getString("NOMFACULTAD")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
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

    private List<Facultad> BuscarFacultad(int IDFACULTAD, Context context) {
        final List<Facultad> facultad = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBFacultad + String.valueOf(IDFACULTAD), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        facultad.add(new Facultad(
                                jsonObject.getString("IDFACULTAD"),
                                jsonObject.getString("NOMFACULTAD")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
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

    private String Eliminar(final Facultad facultad, Context context) {

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
                parametros.put("IDFACULTAD", facultad.getIdFacultad());

                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;
    }

    //CRUD Ubicacion
    private String CrearAct(final Ubicacion ubicacion, Context context, boolean accion) {
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
                parametros.put("IDUBICACION", String.valueOf(ubicacion.getIdUbicacion()));
                parametros.put("IDFACULTAD", ubicacion.getIdFacultad());
                parametros.put("IDPEDIDO", String.valueOf(ubicacion.getIdPedido()));
                parametros.put("DIRECUBICACION", ubicacion.getDirecUbicacion());
                parametros.put("NOMUBICACION", ubicacion.getNomUbicacion());
                parametros.put("PUNTOREFUBICACION", ubicacion.getPuntoRefUbicacion());
                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;
    }

    private List<Ubicacion> BuscarUbicaciones(Context context) {
        final List<Ubicacion> ubicacion = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBUbicaciones, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        ubicacion.add(new Ubicacion(
                                jsonObject.getInt("IDUBICACION"),
                                jsonObject.getString("IDFACULTAD"),
                                jsonObject.getInt("IDPEDIDO"),
                                jsonObject.getString("DIRECUBICACION"),
                                jsonObject.getString("NOMUBICACION"),
                                jsonObject.getString("PUNTOREFUBICACION")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
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

    private List<Ubicacion> BuscarUbicacion(int IDUBICACION, Context context) {
        final List<Ubicacion> ubicacion = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBUbicacion + String.valueOf(IDUBICACION), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        ubicacion.add(new Ubicacion(
                                jsonObject.getInt("IDUBICACION"),
                                jsonObject.getString("IDFACULTAD"),
                                jsonObject.getInt("IDPEDIDO"),
                                jsonObject.getString("DIRECUBICACION"),
                                jsonObject.getString("NOMUBICACION"),
                                jsonObject.getString("PUNTOREFUBICACION")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
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

    private String Eliminar(final Ubicacion ubicacion, Context context) {

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
    private String CrearAct(final PedidoAsignado pedidoAsignado, Context context, boolean accion) {
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
                parametros.put("IDPEDIDOASIGNADO", String.valueOf(pedidoAsignado.getIdPedidoAsignado()));
                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;
    }

    private List<PedidoAsignado> BuscarPedidosAsignados(Context context) {
        final List<PedidoAsignado> pedidoAsignados = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBPedidoAsignados, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        pedidoAsignados.add(new PedidoAsignado(
                                jsonObject.getInt("IDPEDIDO"),
                                jsonObject.getString("IDUSUARIO"),
                                jsonObject.getInt("IDPEDIDOASIGNADO")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
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

    private List<PedidoAsignado> BuscarPedidoAsignado(int IDPEDIDO, String IDUSUARIO, int IDPEDIDOASIGNADO, Context context) {
        final List<PedidoAsignado> pedidoAsignados = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBPedidoAsignado + String.valueOf(IDPEDIDO) + IDUSUARIO + String.valueOf(IDPEDIDOASIGNADO), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        pedidoAsignados.add(new PedidoAsignado(
                                jsonObject.getInt("IDPEDIDO"),
                                jsonObject.getString("IDUSUARIO"),
                                jsonObject.getInt("IDPEDIDOASIGNADO")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
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

    private String Eliminar(final PedidoAsignado pedidoAsignado, Context context) {

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
                parametros.put("IDPEDIDOASIGNADO", String.valueOf(pedidoAsignado.getIdPedidoAsignado()));

                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;
    }
    //CRUD PedidoRealizado
    private String CrearAct(final PedidoRealizado pedidoRealizado, Context context, boolean accion) {
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
                parametros.put("IDPEDIDOASIGNADO", String.valueOf(pedidoRealizado.getIdPedidoRealizado()));
                parametros.put("TIPOPEDREALIZADO", pedidoRealizado.getTipo());
                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;
    }

    private List<PedidoRealizado> BuscarPedidosRealizados(Context context) {
        final List<PedidoRealizado> pedidoRealizados = new ArrayList<>();
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
                                jsonObject.getInt("IDPEDIDOREALIZADO"),
                                jsonObject.getString("TIPOPEDREALIZADO")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
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

    private List<PedidoRealizado> BuscarPedidoRealizado(int IDPEDIDO, String IDUSUARIO, int IDPEDIDOREALIZADO, Context context) {
        final List<PedidoRealizado> pedidoRealizados = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBPedidoRealizado + String.valueOf(IDPEDIDO) + IDUSUARIO + String.valueOf(IDPEDIDOREALIZADO), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        pedidoRealizados.add(new PedidoRealizado(
                                jsonObject.getInt("IDPEDIDO"),
                                jsonObject.getString("IDUSUARIO"),
                                jsonObject.getInt("IDPEDIDOREALIZADO"),
                                jsonObject.getString("TIPOPEDREALIZADO")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
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

    private String Eliminar(final PedidoRealizado pedidoRealizado, Context context) {

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
                parametros.put("IDPEDIDOREALIZADO", String.valueOf(pedidoRealizado.getIdPedidoRealizado()));
                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;
    }

    //CRUD AccesoUsuario
    private String CrearAct(final AccesoUsuario accesoUsuario, Context context, boolean accion) {
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
                parametros.put("IDACCESOUSUARIO", String.valueOf(accesoUsuario.getIdAccesoUsuario()));
                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;
    }

    private List<AccesoUsuario> BuscarAccesoUsuarios(Context context) {
        final List<AccesoUsuario> accesoUsuarios = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBAccesoUsuarios, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        accesoUsuarios.add(new AccesoUsuario(
                                jsonObject.getString("IDUSUARIO"),
                                jsonObject.getString("ID_OPCION"),
                                jsonObject.getInt("IDACCESOUSUARIO")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
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

    private List<AccesoUsuario> BuscarAccesoUsuario(String IDUSUARIO, String IDOPCION, int IDACCESOUSUARIO, Context context) {
        final List<AccesoUsuario> accesoUsuarios = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBAccesoUsuario + IDUSUARIO + IDOPCION + String.valueOf(IDACCESOUSUARIO), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        accesoUsuarios.add(new AccesoUsuario(
                                jsonObject.getString("IDUSUARIO"),
                                jsonObject.getString("ID_OPCION"),
                                jsonObject.getInt("IDACCESOUSUARIO")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
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

    private String Eliminar(final AccesoUsuario accesoUsuario, Context context) {

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
                parametros.put("IDACCESOUSUARIO", String.valueOf(accesoUsuario.getIdAccesoUsuario()));
                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        return resultado;
    }

    //CRUD OpcionCrud
    private String CrearAct(final OpcionCrud opcionCrud, Context context, boolean accion) {
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

    private List<OpcionCrud> BuscarOpcionesCrud(Context context) {
        final List<OpcionCrud> opcionesCrud = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBOpcionesCrud, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        opcionesCrud.add(new OpcionCrud(
                                jsonObject.getString("ID_OPCION"),
                                jsonObject.getString("DESOPCION"),
                                jsonObject.getInt("NUMCRUD")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
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

    private List<OpcionCrud> BuscarOpcionCrud(String IDOPCION, Context context) {
        final List<OpcionCrud> opcionCrud = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URLBOpcionCrud + IDOPCION, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        opcionCrud.add(new OpcionCrud(
                                jsonObject.getString("ID_OPCION"),
                                jsonObject.getString("DESOPCION"),
                                jsonObject.getInt("NUMCRUD")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
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

    private String Eliminar(final OpcionCrud opcionCrud, Context context) {

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


}