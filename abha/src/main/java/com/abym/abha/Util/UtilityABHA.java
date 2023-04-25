package com.abym.abha.Util;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import com.abym.abha.Constants.ApiConstants;
import com.abym.abha.Listener.ResponseListener;
import com.abym.abha.Network.ApiClient;
import com.abym.abha.Network.ApiInterface;
import com.abym.abha.R;
import org.json.JSONArray;
import org.json.JSONObject;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UtilityABHA {

    public static void abhaAPICall(Context context, RelativeLayout progressBar, JSONObject jsonObject, String apiUrl, ResponseListener listener) {
        if (NetworkUtil.checkInternetConnection(context)) {
            if (progressBar != null)
                progressBar.setVisibility(View.VISIBLE);
            ApiInterface apiService = null;
            try {
                apiService = ApiClient.getApiClient(context, ApiConstants.BASEURL).create(ApiInterface.class);

                RequestBody body =
                        RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());

                Call<ResponseBody> call = apiService.abhaRequest(apiUrl, body);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (progressBar != null) progressBar.setVisibility(View.GONE);
                        if (response.isSuccessful()) {
                            if (response.code() == 200) {
                                try {
                                    String responseBody = response.body().string();
                                    listener.onSuccess(responseBody);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            try {
                                JSONObject jsonObject = new JSONObject(response.errorBody().string());
                                if (jsonObject.has("error")) {
                                    JSONObject jsonObject1 = jsonObject.optJSONObject("error");
                                    listener.onFailure(jsonObject1.optString("message"));
                                } else if (jsonObject.has("details")) {
                                    JSONArray jsonArray = jsonObject.optJSONArray("details");
                                    if (jsonArray.length() > 0) {
                                        JSONObject jsonObject1 = jsonArray.optJSONObject(0);
                                        listener.onFailure(jsonObject1.optString("message"));
                                    } else
                                        listener.onFailure(jsonObject.optString("message"));
                                } else if (jsonObject.has("message")) {
                                    listener.onFailure(jsonObject.optString("message"));
                                } else {
                                    listener.onFailure(context.getResources().getString(R.string.unknownerror));
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d("Throwable", t.toString());
                        listener.onFailure(t.toString());
                        if (progressBar != null) progressBar.setVisibility(View.GONE);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static void abhaAPICallwithSessionAuth(Context context, RelativeLayout progressBar, JSONObject jsonObject, String apiUrl, ResponseListener listener) {
        if (NetworkUtil.checkInternetConnection(context)) {
            if (progressBar != null)
                progressBar.setVisibility(View.VISIBLE);
            ApiInterface apiService = null;
            try {
                apiService = ApiClient.getApiClient1(context, ApiConstants.BASEURL).create(ApiInterface.class);

                RequestBody body =
                        RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());

                Call<ResponseBody> call = apiService.abhaRequest(apiUrl, body);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (progressBar != null) progressBar.setVisibility(View.GONE);
                        if (response.isSuccessful()) {
                            if (response.code() == 200) {
                                try {
                                    String responseBody = response.body().string();
                                    listener.onSuccess(responseBody);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            try {
                                JSONObject jsonObject = new JSONObject(response.errorBody().string());
                                if (jsonObject.has("error")) {
                                    JSONObject jsonObject1 = jsonObject.optJSONObject("error");
                                    listener.onFailure(jsonObject1.optString("message"));
                                } else if (jsonObject.has("details")) {
                                    JSONArray jsonArray = jsonObject.optJSONArray("details");
                                    if (jsonArray.length() > 0) {
                                        JSONObject jsonObject1 = jsonArray.optJSONObject(0);
                                        listener.onFailure(jsonObject1.optString("message"));
                                    } else
                                        listener.onFailure(jsonObject.optString("message"));
                                } else if (jsonObject.has("message")) {
                                    listener.onFailure(jsonObject.optString("message"));
                                } else {
                                    listener.onFailure(context.getResources().getString(R.string.unknownerror));
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d("Throwable", t.toString());
                        listener.onFailure(t.toString());
                        if (progressBar != null) progressBar.setVisibility(View.GONE);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static void abhaAPIGetCall(Context context, RelativeLayout progressBar, String apiUrl, ResponseListener listener) {
        if (NetworkUtil.checkInternetConnection(context)) {
            if (progressBar != null)
                progressBar.setVisibility(View.VISIBLE);
            ApiInterface apiService = null;
            try {
                apiService = ApiClient.getApiClient(context, ApiConstants.BASEURL).create(ApiInterface.class);

                Call<ResponseBody> call = apiService.abhaGetRequest(apiUrl);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (progressBar != null) progressBar.setVisibility(View.GONE);
                        if (response.isSuccessful()) {
                            if (response.code() == 200) {
                                try {
                                    String responseBody = response.body().string();
                                    listener.onSuccess(responseBody);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            try {
                                JSONObject jsonObject = new JSONObject(response.errorBody().string());
                                if (jsonObject.has("details")) {
                                    JSONArray jsonArray = jsonObject.optJSONArray("details");
                                    if (jsonArray.length() > 0) {
                                        JSONObject jsonObject1 = jsonArray.optJSONObject(0);
                                        listener.onFailure(jsonObject1.optString("message"));
                                    } else
                                        listener.onFailure(jsonObject.optString("message"));
                                } else if (jsonObject.has("message")) {
                                    listener.onFailure(jsonObject.optString("message"));
                                } else {
                                    listener.onFailure(context.getResources().getString(R.string.unknownerror));
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d("Throwable", t.toString());
                        listener.onFailure(t.toString());
                        if (progressBar != null) progressBar.setVisibility(View.GONE);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static void abhaAPIGetCallwithSessionAuth(Context context, RelativeLayout progressBar, String apiUrl, ResponseListener listener) {
        if (NetworkUtil.checkInternetConnection(context)) {
            if (progressBar != null)  progressBar.setVisibility(View.VISIBLE);
            ApiInterface apiService = null;
            try {
                apiService = ApiClient.getApiClient1(context, ApiConstants.BASEURL).create(ApiInterface.class);

                Call<ResponseBody> call = apiService.abhaGetRequest(apiUrl);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (progressBar != null)    progressBar.setVisibility(View.GONE);
                        if (response.isSuccessful()) {
                            if (response.code() == 200) {
                                try {
                                    String responseBody = response.body().string();
                                    listener.onSuccess(responseBody);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            try {
                                JSONObject jsonObject = new JSONObject(response.errorBody().string());
                                if (jsonObject.has("error")) {
                                    JSONObject jsonObject1 = jsonObject.optJSONObject("error");
                                    listener.onFailure(jsonObject1.optString("message"));
                                } else if (jsonObject.has("details")) {
                                    JSONArray jsonArray = jsonObject.optJSONArray("details");
                                    if (jsonArray.length() > 0) {
                                        JSONObject jsonObject1 = jsonArray.optJSONObject(0);
                                        listener.onFailure(jsonObject1.optString("message"));
                                    } else
                                        listener.onFailure(jsonObject.optString("message"));
                                } else if (jsonObject.has("message")) {
                                    listener.onFailure(jsonObject.optString("message"));
                                } else {
                                    listener.onFailure(context.getResources().getString(R.string.unknownerror));
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d("Throwable", t.toString());
                        listener.onFailure(t.toString());
                        if (progressBar != null)    progressBar.setVisibility(View.GONE);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static void abhaAPIGetCallwithUserAuth(Context context, RelativeLayout progressBar, JSONObject jsonObject, String apiUrl, ResponseListener listener) {
        if (NetworkUtil.checkInternetConnection(context)) {
            if (progressBar != null)   progressBar.setVisibility(View.VISIBLE);
            ApiInterface apiService = null;
            try {
                apiService = ApiClient.getApiClient2(context, ApiConstants.BASEURL).create(ApiInterface.class);

                RequestBody body =
                        RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());

                Call<ResponseBody> call = apiService.abhaRequest(apiUrl, body);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (progressBar != null) progressBar.setVisibility(View.GONE);
                        if (response.isSuccessful()) {
                            if (response.code() == 200) {
                                try {
                                    String responseBody = response.body().string();
                                    listener.onSuccess(responseBody);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            try {
                                JSONObject jsonObject = new JSONObject(response.errorBody().string());
                                if (jsonObject.has("details")) {
                                    JSONArray jsonArray = jsonObject.optJSONArray("details");
                                    if (jsonArray.length() > 0) {
                                        JSONObject jsonObject1 = jsonArray.optJSONObject(0);
                                        listener.onFailure(jsonObject1.optString("message"));
                                    } else
                                        listener.onFailure(jsonObject.optString("message"));
                                } else if (jsonObject.has("message")) {
                                    listener.onFailure(jsonObject.optString("message"));
                                } else {
                                    listener.onFailure(context.getResources().getString(R.string.unknownerror));
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d("Throwable", t.toString());
                        listener.onFailure(t.toString());
                        if (progressBar != null)    progressBar.setVisibility(View.GONE);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
