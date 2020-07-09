package com.cskaoyan.project1.controller.admin;

import com.cskaoyan.project1.model.Result;
import com.cskaoyan.project1.model.Type;
import com.cskaoyan.project1.model.bo.goods.AddGoodsBO;
import com.cskaoyan.project1.model.bo.goods.AddTypeBO;
import com.cskaoyan.project1.model.bo.goods.ReplyBO;
import com.cskaoyan.project1.model.bo.goods.UpdateGoodsBO;
import com.cskaoyan.project1.model.vo.goods.GoodsInfoVO;
import com.cskaoyan.project1.model.vo.goods.MsgVO;
import com.cskaoyan.project1.model.vo.goods.TypeGoodsVO;
import com.cskaoyan.project1.service.GoodsService;
import com.cskaoyan.project1.service.GoodsServiceImpl;
import com.cskaoyan.project1.utils.FileUploadUtils;
import com.cskaoyan.project1.utils.HttpUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @auther Ningbo Tien
 * @date 2020-07-06 16:12
 */
@WebServlet("/api/admin/goods/*")
public class GoodsServlet extends HttpServlet {

    private GoodsService goodsService = new GoodsServiceImpl();
    private Result result = new Result();
    private Gson gson = new Gson();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/goods/", "");
        if("reply".equals(action)){
            reply(request, response);
        }else if("addType".equals(action)){
            addType(request, response);
        }else if("imgUpload".equals(action)){
            imgUpload(request, response);
        }else if("addGoods".equals(action)){
            addGoods(request, response);
        }else if("updateGoods".equals(action)){
            updateGoods(request, response);
        }
    }

    /**
     * 修改商品信息
     * @param request
     * @param response
     */
    private void updateGoods(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String requestBody = HttpUtils.getRequestBody(request);
        UpdateGoodsBO updateGoodsBO = gson.fromJson(requestBody, UpdateGoodsBO.class);
        goodsService.updateGoods(updateGoodsBO);
        response.getWriter().println(gson.toJson(Result.ok("修改成功！")));
    }

    /**
     * 添加商品
     * 1.获取请求体中数据；
     * 2.处理
     * 3.做出响应
     * @param request
     * @param response
     */
    private void addGoods(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String requestBody = HttpUtils.getRequestBody(request);
        AddGoodsBO addGoodsBO = gson.fromJson(requestBody, AddGoodsBO.class);
        goodsService.addGoodsBO(addGoodsBO);
        response.getWriter().println(gson.toJson(Result.ok("添加成功！")));

    }

    /**
     * 上传商品图片
     * commons-fileUpload
     * @param request
     * @param response
     */
    private void imgUpload(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //map中保存的键值对为上传文件的fieldName和保存路径
        Map<String, Object> map = FileUploadUtils.parseRequest(request);
        String file = (String) map.get("file");
        //图片在服务端保存，需要写全路径，使用配置文件满足不同环境，application.properties
        //使用context域从application.properties中取domain，使用listener
        String domain = (String) getServletContext().getAttribute("domain");
        response.getWriter().println(gson.toJson(Result.ok(domain + file)));

    }

    /**
     * 添加商品类目
     * @param request
     * @param response
     */
    private void addType(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String requestBody = HttpUtils.getRequestBody(request);
        AddTypeBO addTypeBO = gson.fromJson(requestBody, AddTypeBO.class);
        String message = goodsService.addType(addTypeBO);
        if("添加成功！".equals(message)){
            response.getWriter().println(gson.toJson(Result.ok(message)));
        }else{
            response.getWriter().println(gson.toJson(Result.error("该类目已存在！")));
        }
    }

    /**
     * 回复留言
     * @param request
     * @param response
     */
    private void reply(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String requestBody = HttpUtils.getRequestBody(request);
        ReplyBO replyBO = gson.fromJson(requestBody, ReplyBO.class);
        goodsService.reply(replyBO);
        response.getWriter().println(gson.toJson(Result.ok("已回复该用户！")));

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        String action = requestURI.replace("/api/admin/goods/", "");

        if("noReplyMsg".equals(action)){
            noReplyMsg(request, response);
        }else if("repliedMsg".equals(action)){
            repliedMsg(request, response);
        }else if("getType".equals(action)){
            getType(request, response);
        } else if("deleteType".equals(action)){
            deleteType(request, response);
        }else if("getGoodsByType".equals(action)){
            getGoodsByType(request, response);
        }else if("getGoodsInfo".equals(action)){
            getGoodsInfo(request, response);
        }else if("deleteGoods".equals(action)){
            deleteGoods(request, response);
        }
    }

    /**
     * 删除某个类目
     * 先删除该类目下所有商品，再删除类目
     * @param request
     * @param response
     */
    private void deleteType(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Integer typeId = Integer.parseInt(request.getParameter("typeId"));
        goodsService.deleteType(typeId);
        response.getWriter().println(gson.toJson(Result.ok("删除成功，请刷新！")));
    }

    /**
     * 删除商品
     * @param request
     * @param response
     */
    private void deleteGoods(HttpServletRequest request, HttpServletResponse response) {

        Integer id = Integer.parseInt(request.getParameter("id"));
        goodsService.deleteGoods(id);
        try {
            response.getWriter().println(gson.toJson(Result.ok()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 编辑商品时显示商品详细信息
     * @param request
     * @param response
     */
    private void getGoodsInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String id = request.getParameter("id");
        GoodsInfoVO goodsInfoVO = goodsService.getGoodsInfo(id);
        response.getWriter().println(gson.toJson(Result.ok(goodsInfoVO)));
    }

    /**
     * 后台 显示当前类目下商品
     * @param request
     * @param response
     */
    private void getGoodsByType(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String typeId = request.getParameter("typeId");
        List<TypeGoodsVO> typeGoodsVOList = goodsService.getGoodsByType(typeId);
        response.getWriter().println(gson.toJson(Result.ok(typeGoodsVOList)));
    }

    /**
     * 显示所有商品类目
     * @param request
     * @param response
     */
    private void getType(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<Type> typeList = goodsService.getType();
        response.getWriter().println(gson.toJson(Result.ok(typeList)));
    }

    /**
     * 显示已经回复的留言
     * @param request
     * @param response
     * @throws IOException
     */
    private void repliedMsg(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<MsgVO> msgVOList = goodsService.repliedMsg();
        response.getWriter().println(gson.toJson(Result.ok(msgVOList)));
    }

    /**
     * 显示所有未回复的留言
     * @param request
     * @param response
     */
    private void noReplyMsg(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //返回的未回复留言是一个集合
        List<MsgVO> msgVOList = goodsService.noReplyMsg();
        result.setCode(0);
        result.setData(msgVOList);
        response.getWriter().println(gson.toJson(result));
    }
}
