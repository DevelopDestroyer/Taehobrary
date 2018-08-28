/*

	POST로 보내뿌리기
	응답은 그냥 문자열로 받아옴

*/

    public int Regist(String a, String b){

    	//System.out.println(mvm);
    	RestTemplate template = new RestTemplate();
    	Map<String, String> mvm = new HashMap<String, String>();
    	int res = 0;
    	ItemInfo ItemInfo = new ItemInfo();	//이 녀석은 하단에 샘플로 정의해 놓았음
    	ItemInfo.setA(a);
    	ItemInfo.setB(b);

    		

		String tokenToJson = null;
		template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		try {
			tokenToJson = new ObjectMapper().writeValueAsString(ItemInfo);
			
		} catch (JsonProcessingException e) {
			// TODO : ErrorCode 정의 할 것
			e.printStackTrace();
		}
		
		System.out.println(ItemInfo);
		System.out.println(AGENT_ADDRESS);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		
		HttpEntity<String> entity = new HttpEntity<String>(tokenToJson, headers);
		
    	
		String result;
		
		try{
			ResponseEntity<String> response = template.exchange("http://localhost:1234/xx", HttpMethod.POST, entity, String.class);
			System.out.println("***** 응답 : " + response.getBody());
			res = 1;
		}catch(HttpClientErrorException e){
			res = 0;
			e.printStackTrace();
		}
		

    	return res;
    }
	
	
	
	
public class ItemInfo {
	private String a;
	private String b;


	public ItemInfo(){
		
	}
	
	public ItemInfo(@JsonProperty("a") String a, 
			@JsonProperty("b") String b) {
		super();
		this.a = a;
		this.b = b;
	}

	public String getA() {
		return a;
	}

	public void setA(String aa) {
		this.a = aa;
	}

	public String getB() {
		return b;
	}

	public void setB(String bb) {
		this.b = bb;
	}


	@Override
	public String toString() {
		return "ItemInfo [a=" + a + ", b=" + b + "]";
	}

}


/*

	GET으로 보내뿌리기
	응답은 JSON 형태로 받아옴

*/

	

    public ArrayList<HashMap<String, String>> contentsList(){
 
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		URI uri = URI.create("http://test.com/list");
		RestTemplate template = new RestTemplate();
		String responseString = template.getForObject(uri, String.class);
		System.out.println("***전체목록 불러오기 : " + responseString);
		
		
		ObjectMapper objectMapper = new ObjectMapper();
		TypeFactory typeFactory = objectMapper.getTypeFactory();
		try {
			List<contentsObject> someClassList = objectMapper.readValue(responseString, typeFactory.constructCollectionType(List.class, contentsObject.class));//contentsObject의 구현은 상기 ItemInfo처럼 하면댐
			
			for(int i = 0; i < someClassList.size(); i++) {
				System.out.println("지금은 " + i + "번째");
				if(null == someClassList.get(i))
					break;
				list.add(addListManager2(someClassList.get(i)));
				
			}
			
			
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
    	return list;
    }
    
	
	public static HashMap<String, String> addListManager2(contentsObject item){
    	
		HashMap<String, String> mapTMP = new HashMap<String, String>();
		
		mapTMP.put("a", item.getA().toString());
		mapTMP.put("b", item.getB().toString());
		
		
		
		return mapTMP;
    }