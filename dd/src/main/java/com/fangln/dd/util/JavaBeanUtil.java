package com.fangln.dd.util;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JavaBeanUtil {

	private static ConcurrentHashMap<Class<?>, Map<String,GetSetMethod>> methodCaches=new ConcurrentHashMap<Class<?>, Map<String,GetSetMethod>>();
	
	public static class GetSetMethod{
		private Method getMethod;
		private Method setMethod;
		public Method getGetMethod() {
			return getMethod;
		}

		public Method getSetMethod() {
			return setMethod;
		}
		
	}
	
	public static Map<String,GetSetMethod> getGetSetMethods(Class<?> pojoClass){
		Map<String,GetSetMethod> methodsMap=methodCaches.get(pojoClass);
		if(methodsMap==null){
			methodsMap=new LinkedHashMap<String,GetSetMethod>();
			Method[] methods = pojoClass.getMethods();
		    String getClass="getClass";
			for (Method method : methods) {
		      String name = method.getName();
		      if(getClass.equals(name)){
		    	  continue;
		      }
		      if (name.startsWith("set") && name.length() > 3) {
		        if (method.getParameterTypes().length == 1) {
		           name = methodToProperty(name);
		           if(name!=null){
		        	   GetSetMethod g=methodsMap.get(name);
		        	   if(g==null){
		        		   g=new GetSetMethod();
		        		   methodsMap.put(name, g);
		        	   }
		        	   g.setMethod=method;
		           }
		        }
		      }else if ((name.startsWith("get") && name.length() > 3)||(name.startsWith("is") && name.length() > 2)) {
		          if (method.getParameterTypes().length == 0) {
		              name = methodToProperty(name);
		              if(name!=null){
			        	   GetSetMethod g=methodsMap.get(name);
			        	   if(g==null){
			        		   g=new GetSetMethod();
			        		   methodsMap.put(name, g);
			        	   }
			        	   g.getMethod=method;
			          }
		          }
	          } 
		    }
			
			methodCaches.put(pojoClass, methodsMap);
		}
		
		
	    return methodsMap;
	 }
	
	 public static String methodToProperty(String name) {
	    if (name.startsWith("is")) {
	      name = name.substring(2);
	    } else if (name.startsWith("get") || name.startsWith("set")) {
	      name = name.substring(3);
	    } else {
	       return null;
	    }

	    if (name.length() == 1 || (name.length() > 1 && !Character.isUpperCase(name.charAt(1)))) {
	      name = name.substring(0, 1).toLowerCase(Locale.ENGLISH) + name.substring(1);
	    }

	    return name;
	 }

	  public static boolean isProperty(String name) {
	      return name.startsWith("get") || name.startsWith("set") || name.startsWith("is");
	  }

	  public static boolean isGetter(String name) {
	      return name.startsWith("get") || name.startsWith("is");
	  }

	  public static boolean isSetter(String name) {
	      return name.startsWith("set");
	  }
	  
	  
	  
	  /**
		 * 将数据转换成Map<String,String> 类型, 但是如果值为自定义javabean, collection, map 不转换
		 * @param pojo 数据,可以是Map,javaBean
		 * @return
		 */
		public static Map<String,Object> toStringMap(Object pojo){
			if(pojo==null){
				return null;
			}
			
			Map<String,Object> returnMap=new LinkedHashMap<String,Object>();
			SimpleDateFormat df=null;
			SimpleDateFormat df1=null;
			SimpleDateFormat df2=null;
			if(pojo instanceof Map){
				Map<?,?> x=(Map<?,?>)pojo;
				Object zv;
			    for(Map.Entry<?, ?> e:x.entrySet()){
					if(e.getKey()!=null&&(e.getKey() instanceof String)){
						Object va=e.getValue();
						
						if(va!=null){
							if(va instanceof java.sql.Date){
								if(df1==null){
									df1=new SimpleDateFormat("yyyy-MM-dd");
								}
								df=df1;
							}else if(va instanceof java.util.Date){
								if(df2==null){
									df2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								}
								df=df2;
							}
						}
						
						zv=toStringObject(va, df);
						if(zv!=null){
							returnMap.put(e.getKey().toString(), zv);
						}
					}
				}
			}else{
				//pojo
				Map<String,GetSetMethod> methodMap=getGetSetMethods(pojo.getClass());
				if(methodMap!=null){
					Object[] t=new Object[0];
				
					Object zv;
					for(Map.Entry<String,GetSetMethod> x1:methodMap.entrySet()){
						if(x1.getKey()!=null&&x1.getValue()!=null&&x1.getValue().getGetMethod()!=null){
						    try{
								Object va=x1.getValue().getGetMethod().invoke(pojo, t);
								if(va!=null){
									if(va instanceof java.sql.Date){
										if(df1==null){
											df1=new SimpleDateFormat("yyyy-MM-dd");
										}
										df=df1;
									}else if(va instanceof java.util.Date){
										if(df2==null){
											df2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
										}
										df=df2;
									}
								}
								
								zv=toStringObject(va, df);
								if(zv!=null){
									returnMap.put(x1.getKey(), zv);
								}
								
						    }catch(Exception ex){
						    	throw new RuntimeException("获取属性值异常:类："+pojo.getClass()+",属性:"+x1.getKey()+",原因:"+ex.getMessage());
						    }
					
						}
					}
				}
				
			}
			
			return returnMap;
			
		}
		
		private static Object toStringObject(Object value, SimpleDateFormat df){
			if(value==null){
				return null;
			}
			Object v;
			if(value instanceof java.util.Date){
				 v=df.format((java.util.Date)value);
			}else if(value instanceof String
					||value instanceof Byte
					||value instanceof Short
					||value instanceof Integer
					||value instanceof Long
					||value instanceof Float
					||value instanceof Double
					||value instanceof BigDecimal){
				v=value.toString();
			}else{
				v=value;
			}
			return v;
		}
	
}
