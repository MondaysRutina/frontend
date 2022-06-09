package com.cso.rutina;

public class CosmeticAdapter{
//public class CosmeticAdapter extends BaseAdapter {

//    private Context context;
//    private List<Cosmetic> cosmeticList;
//
//    public CosmeticAdapter(Context, List<Cosmetic> cosmeticList) {
//        this.context = context;
//        this.cosmeticList = cosmeticList;
//    }
//    @Override
//    public int getCount() {
//        return cosmeticList.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return cosmeticList.get(i);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return i;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        View v = View.inflate(context,R.layout.cosmetic);
//        return null;
//    }

//    public CosmeticAdapter(Context context, List<Cosmetic> cosmeticList) {
//        this.context = context;
//        this.cosmeticList = cosmeticList;
//    }
//
//    // 출력할 총 갯수흫 설정하는 메소드
//    @Override
//    public int getCount() {
//        return cosmeticList.size();
//    }
//
//    // 특정 화장품 타입을 반환하는 메소드
//    @Override
//    public Object getItem(int i){
//        return cosmeticList.get(i);
//    }
//
//    // 아이템별 이름을 반환하는 메소드
//    @Override
//    public long getItemId(int i) {
//        return i;
//    }
//
//    //가장 중요한 부분
//    @Override
//    public View getView(int i, View view, ViewGroup viewGroup) {
//        View v = View.inflate(context, R.layout.activity_cosmetic, null);
//        //뷰에 다음 컴포넌트들을 연결시켜줌
//         TextView api_cosmetic_type = (TextView)v.findViewById(R.id.api_cosmetic_type);
//         TextView api_cosmetic_name = (TextView)v.findViewById(R.id.api_cosmetic_name);
//         TextView api_cosmetic_ph = (TextView)v.findViewById(R.id.api_cosmetic_ph);
//         api_cosmetic_type.setText(cosmeticList.get(i).getApi_cosmetic_type());
//         api_cosmetic_name.setText(cosmeticList.get(i).getApi_cosmetic_name());
//         api_cosmetic_ph.setText(cosmeticList.get(i).getApi_cosmetic_ph());
//
//         //이렇게하면 findViewWithTag를 쓸 수 있음 없어도 되는 문장임
//         v.setTag(cosmeticList.get(i).getApi_cosmetic_type());
//        // 만든뷰를 반환함
//        return v;
 }




    /*// 서버 URL 설정 ( PHP 파일 연동 )
    final static private String URL = "http://52.78.222.77:8080/api_cosmetics.php";
    private Map<String, String> parameters;

    public com.cso.rutina.com.cso.rutina.Cosmetic.CosmeticRequest(String id, String pwd, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        parameters = new HashMap<>();
        parameters.put("id", id);
        parameters.put("pwd", pwd);

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return parameters;
    } */
