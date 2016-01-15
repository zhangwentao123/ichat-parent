package com.thebo.framework.util;

import javax.servlet.http.HttpSession;

import com.thebo.framework.entity.BaseUser;
import com.thebo.framework.constants.SysConstants;

public class SessionUtils {
	
	private static ThreadLocal<BaseUser> user = new ThreadLocal<BaseUser>();
	
	public static BaseUser getUser() {
		return user.get();
	}

	public static BaseUser getUser(HttpSession session) {
		return (BaseUser) session.getAttribute(SysConstants.USER_SESSION_KEY);
	}
	
	public static boolean setUser(HttpSession session){
		if(session!=null&&session.getAttribute(SysConstants.USER_SESSION_KEY)!=null){
			user.set((BaseUser)session.getAttribute(SysConstants.USER_SESSION_KEY));
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean login(HttpSession session) {
		boolean result = true;
		if(session!=null&&session.getAttribute(SysConstants.USER_SESSION_KEY)!=null){
			result = result && setUser(session);
			result = result && setUserRoles(session);
//			result = result && setResourcesMap(session,false);
			return result;
		}else{
			return false;
		}
	}

	public static void logout(HttpSession session) {
		session.removeAttribute(SysConstants.USER_SESSION_KEY);
		session.removeAttribute(SysConstants.USER_ROLES_SESSION_KEY);
		session.removeAttribute(SysConstants.USER_ROLES_SESSION_KEY);
	}
	//增加用户拥有的角色
	private static boolean setUserRoles(HttpSession session){
		if(session!=null&&session.getAttribute(SysConstants.USER_SESSION_KEY)!=null){
//			RoleService roleService = (RoleService) ContextUtils.getBean(RoleService.class);
			BaseUser user = getUser();
//			List<Role> roleList = roleService.getByUserId(user.getId());
//			user.setRoleList(roleList);
//			session.setAttribute(SysConstants.USER_ROLES_SESSION_KEY, roleList);
			return true;
		}else{
			return false;
		}
	}
	
//	@SuppressWarnings("unchecked")
//	public static List<Role> getUserRoles(HttpSession session){
//		if(session.getAttribute(SysConstants.USER_ROLES_SESSION_KEY)!=null){
//			return (List<Role>)session.getAttribute(SysConstants.USER_ROLES_SESSION_KEY);
//		}
//		return null;
//	}
	
//	@SuppressWarnings("unchecked")
//	public static List<Resources> getUserResources(HttpSession session){
//		if(session.getAttribute(SysConstants.USER_RESOURCES_SESSION_KEY)!=null){
//			return (List<Resources>)session.getAttribute(SysConstants.USER_RESOURCES_SESSION_KEY);
//		}
//		return null;
//	}
	
	//增加用户所能访问的菜单
//    private static boolean setResourcesMap(HttpSession session, boolean checkIfEmpty) {
//        if (checkIfEmpty && session.getAttribute(SysConstants.USER_RESOURCES_FOR_PAGE) != null) {
//            return true;
//        }
//        if(getUserResources(session)==null){
//        	if(getUserRoles(session)!=null){
//            	List<Role> roleList = (List<Role>)getUserRoles(session);
//            	if(!roleList.isEmpty()){
//            		List<String> roleIds = new ArrayList<String>();
//                	for(Role role:(List<Role>)getUserRoles(session)){
//                		roleIds.add(role.getId());
//                	}
//                	ResourcesService resourcesService = (ResourcesService) ContextUtils.getBean(ResourcesService.class);
//                	List<Resources> resources = resourcesService.getByRoleIds(roleIds);
//                	
//                	
//                	JSONArray results = new JSONArray();
//                	ConcurrentMap<String, List<Resources>> map = new ConcurrentHashMap<String, List<Resources>>();
//                    for (Resources resource : resources) {
//                    	if(resource.getType().equals("MENU")){
//                    		if(map.get(resource.getParentId())!=null){
//                    			map.get(resource.getParentId()).add(resource);
//                    		}else{
//                    			List<Resources> list = new ArrayList<Resources>();
//                    			list.add(resource);
//                    			map.put(resource.getParentId(), list);
//                    		}
//                    	}
//                    }
//                    String parentId = SysConstants.ROOT_ID;
//                    List<Resources> list = map.get(parentId);
//                    Collections.sort(list,new Comparator<Resources>(){
//    					@Override
//    					public int compare(Resources o1, Resources o2) {
//    						if(o1.getSeq()==null&&o2.getSeq()==null){
//    							return 0;
//    						}else if(o1.getSeq()==null){
//    							return -1;
//    						}else if(o2.getSeq()==null){
//    							return 1;
//    						}else {
//    							return o1.getSeq()-o2.getSeq();
//    						}
//    					}});
//                    		
//                    for (Resources resource : list) {
//                        JSONObject item = new JSONObject();
//                        item.put("text", resource.getName());
//                        parentId = resource.getId();
//                        loadChilds(map, parentId, item, 0);
//                        results.add(item);
//                    }
//                    session.setAttribute(SysConstants.USER_RESOURCES_SESSION_KEY, resources);
//                    session.setAttribute(SysConstants.USER_RESOURCES_FOR_PAGE, results);
//            	}
//            	
//                return true;
//            }else{
//            	return false;
//            }
//        }else{
//        	return true;
//        }
//        
//        
//        
//    }

//    private static void loadChilds(ConcurrentMap<String, List<Resources>> map, String parentId, JSONObject parent, int level) {
//        String maxDepth = WebContextUtils.getPropValue(PropKeyConstants.MENU_MAX_DEPTH);
//        int menuMaxDepth;
//        try {
//            menuMaxDepth = Integer.parseInt(maxDepth);
//        } catch (NumberFormatException e) {
//            throw new ErrorBasConfigException();
//        }
//        if (level >= menuMaxDepth) {
//            return;
//        }
//        List<Resources> list = map.get(parentId);
//        if (list==null||list.isEmpty()) {
//            return;
//        }
//        Collections.sort(list,new Comparator<Resources>(){
//			@Override
//			public int compare(Resources o1, Resources o2) {
//				if(o1.getSeq()==null&&o2.getSeq()==null){
//					return 0;
//				}else if(o1.getSeq()==null){
//					return -1;
//				}else if(o2.getSeq()==null){
//					return 1;
//				}else {
//					return o1.getSeq()-o2.getSeq();
//				}
//			}});
//        
//        JSONObject menu = new JSONObject();
//        parent.put("menu", menu);
//        JSONArray items = new JSONArray();
//        menu.put("items", items);
//        for (Resources resource : list) {
//            JSONObject item = new JSONObject();
//        	item.put("text", resource.getName());
//            item.put("href", "javascript:openTab(&#39;" + resource.getName() + "&#39;, &#39;" + resource.getUri() + "&#39;)");
//            parentId = resource.getId();
//            loadChilds(map, parentId, item, level + 1);
//            items.add(item);
//        }
//    }

	public static void setUser(BaseUser parm) {		 
		user.set(parm);	
	}

}