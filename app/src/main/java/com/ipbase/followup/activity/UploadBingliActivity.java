package com.ipbase.followup.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.ipbase.followup.R;
import com.ipbase.followup.activity.base.AbsViewActivity;
import com.ipbase.followup.adapter.BingliAdapter;
import com.ipbase.followup.adapter.PopListAdapter;
import com.ipbase.followup.bean.Bingli;
import com.ipbase.followup.model.bean.User;
import com.ipbase.followup.widget.TitleBar;
import com.kesar.mvp.presenter.impl.MainPresenter;
import com.kesar.mvp.view.IMainView;
import com.kesar.mvp.view.ITitleBarView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class UploadBingliActivity extends AbsViewActivity<MainPresenter> implements IMainView, ITitleBarView, TitleBar.BtnClickListener {


    @Bind(R.id.tbv_titlebar_upload)
    TitleBar tbvTitlebarUpload;
    @Bind(R.id.et_zhuYaoZhengZhuang)
    EditText etZhuYaoZhengZhuang;
    @Bind(R.id.et_zhenDuan)
    EditText etZhenDuan;
    @Bind(R.id.et_faBingGuoCheng)
    EditText etFaBingGuoCheng;
    @Bind(R.id.ll_jiuZhenXinXi)
    LinearLayout llJiuZhenXinXi;
    @Bind(R.id.btn_jiuZhenXinXi)
    ImageButton btnJiuZhenXinXi;
    @Bind(R.id.btn_RenYuanXinXi)
    ImageButton btnRenYuanXinXi;
    @Bind(R.id.ll_RenYuanXinXi)
    LinearLayout llRenYuanXinXi;
    private LayoutInflater layoutInflater;
    private static List<User> patientDatalist = new ArrayList<>();
    PopListAdapter popListAdapter;
    ListView myListView;
    public static User patientUser;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_upload_bingli;
    }

    @Override
    protected MainPresenter buildPresenter() {
        return MainPresenter.build(this);
    }

    @Override
    public void initView() {
        // 实例化布局对象
        layoutInflater = LayoutInflater.from(this);
        //实例化标题栏View
        tbvTitlebarUpload.setTitleBarListener(this);
        setTitle("添加病历");
        setLeftBtnVisable(true);
        setRightBtnVisable(true);
        setRightBtnImage(R.drawable.icon_save);
        setRightToLeftBtnVisable(false);
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    public void showToast(String text) {
        toast(text);
    }

    @Override
    public void leftClick() {
        dialogFinish();
    }

    @Override
    public void rightClick() {
        UploadBingliData();
    }

    @Override
    public void rightToLeftClick() {
        getPresenter().rightToLeftClick();
    }

    /**
     * 设置标题
     *
     * @param title
     */
    @Override
    public void setTitle(String title) {
        if (tbvTitlebarUpload != null) {
            tbvTitlebarUpload.setTitle(title);
        }
    }

    /**
     * 设置左侧按钮是否可见
     *
     * @param flag 是否可见
     */
    @Override
    public void setLeftBtnVisable(boolean flag) {
        if (tbvTitlebarUpload != null) {
            tbvTitlebarUpload.setLeftBtnVisable(flag);
        }
    }

    /**
     * 设置右侧靠左的按钮是否可见
     *
     * @param flag
     */
    @Override
    public void setRightToLeftBtnVisable(boolean flag) {
        if (tbvTitlebarUpload != null) {
            tbvTitlebarUpload.setRightToLeftBtnVisable(flag);
        }
    }

    /**
     * 设置右侧按钮是否可见
     *
     * @param flag 是否可见
     */
    @Override
    public void setRightBtnVisable(boolean flag) {
        if (tbvTitlebarUpload != null) {
            tbvTitlebarUpload.setRightBtnVisable(flag);
        }
    }

    /**
     * 设置右侧按钮的图标
     *
     * @param resId
     */
    @Override
    public void setRightBtnImage(int resId) {
        if (tbvTitlebarUpload != null) {
            tbvTitlebarUpload.setRightBtnImage(resId);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.et_zhuYaoZhengZhuang)
    public void onDtZhuYaoZhengZhuangClicked() {
    }

    @OnClick(R.id.et_zhenDuan)
    public void onDtZhenDuanClicked() {
    }

    @OnClick(R.id.et_faBingGuoCheng)
    public void onEtFaBingGuoChengClicked() {
    }

    @OnClick(R.id.ll_jiuZhenXinXi)
    public void onLlJiuZhenXinXiClicked() {
        toast("跳转到病人信息");
    }

    @OnClick(R.id.btn_jiuZhenXinXi)
    public void onViewClicked() {
        toast("btn跳转到病人信息");
    }

    protected void dialogFinish() //删除确认对话框
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("");
        builder.setMessage("确认直接返回？");

        builder.setIcon(android.R.drawable.ic_dialog_info);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 点击“确认”后的操作
                finish();
            }
        });
        builder.setNegativeButton("返回", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 点击“返回”后的操作
            }
        });

        AlertDialog ad = builder.create();
        ad.show();

    }


    @OnClick(R.id.btn_RenYuanXinXi)
    public void onBtnRenYuanXinXiClicked() {
        showDialogChoosePatient();
    }

    @OnClick(R.id.ll_RenYuanXinXi)
    public void onLlRenYuanXinXiClicked() {
        showDialogChoosePatient();
    }

    private void UploadBingliData() {
        if (etZhuYaoZhengZhuang.getText().toString().equals("") ||
                etZhenDuan.getText().toString().equals("") ||
                etFaBingGuoCheng.getText().toString().equals("")) {
            toast("信息不全");
            return;
        }

        Bingli bingli = new Bingli();
        bingli.setDoctor(BmobUser.getCurrentUser());
        if(patientUser!=null)
        {
            bingli.setPatient(patientUser);
            bingli.setName(patientUser.getRealName());
            bingli.setAge(patientUser.getAge()+"");

            if(patientUser.getSex()==0)
            {
                bingli.setGender("男");
            }else
            {
                bingli.setGender("女");
            }
            if(bingli.getAge().equals("0"))
            {
                bingli.setAge("23");
            }
        }else
        {
            bingli.setName("老王");
            bingli.setAge("23");
            bingli.setGender("男");
        }

        bingli.setReadFlag(false);
        bingli.setZhengzhuang(etZhuYaoZhengZhuang.getText().toString());
        bingli.setZhenduan(etZhenDuan.getText().toString());
        bingli.setFabing(etFaBingGuoCheng.getText().toString());
        bingli.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    toast("添加成功");
                    finish();
                } else {
                    toast("添加失败" + e.getMessage());
                }
            }
        });
    }

    public void initList() {//创建病例数据列表
        popListAdapter = new PopListAdapter(getContext(), patientDatalist);
        myListView.setAdapter(popListAdapter);
    }
    private List<User> getPatientDataList() {
        BmobQuery<User> bmobQuery1 = new BmobQuery<>();
        bmobQuery1.order("-createdAt");
        bmobQuery1.setLimit(100);
        bmobQuery1.addQueryKeys("objectId");

        bmobQuery1.findObjects(new FindListener<User>() {
            @Override
            public void done(List<User> object, BmobException e) {
                if (e == null) {
                    patientDatalist=object;
                    BmobQuery<User> bmobQuery2= new BmobQuery<>();
                    bmobQuery2.addQueryKeys("username");
                    bmobQuery2.setLimit(100);
                    bmobQuery2.order("-createdAt");
                    bmobQuery2.findObjects(new FindListener<User>() {
                        @Override
                        public void done(List<User> object, BmobException e) {
                            if (e == null) {
                                for(int i=0;i<object.size();i++)
                                {
                                    patientDatalist.get(i).setUsername(object.get(i).getUsername());
                                }
                                BmobQuery<User> bmobQuery3 = new BmobQuery<>();
                                bmobQuery3.addQueryKeys("realName");
                                bmobQuery3.setLimit(100);
                                bmobQuery3.order("-createdAt");
                                bmobQuery3.findObjects(new FindListener<User>() {
                                    @Override
                                    public void done(List<User> object, BmobException e) {
                                        if (e == null) {
                                            for(int i=0;i<object.size();i++)
                                            {
                                                patientDatalist.get(i).setRealName(object.get(i).getRealName());
                                            }
                                            initList();
                                            toast("共" + object.size() + "人");
                                        } else {
                                            toast("查询失败：" + e.getMessage() + "," + e.getErrorCode());
                                        }
                                    }
                                });
                            } else {
                            }
                        }
                    });
                } else {
                }
            }
        });


        return patientDatalist;
    }

    private void showDialogChoosePatient()//选择病人弹出对话框
    {
        AlertDialog.Builder builder;
        final AlertDialog alertDialog;
        Context context = getApplicationContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.pop_listview, (ViewGroup)findViewById(R.id.lv_poplistView));
        myListView = (ListView) layout.findViewById(R.id.lv_poplistView);
        getPatientDataList();


        builder = new AlertDialog.Builder(this);//这里只能传this
        builder.setView(layout);
        alertDialog = builder.create();
        alertDialog.show();
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                patientUser=patientDatalist.get(position);
                toast("已选择"+patientUser.getRealName());
                alertDialog.dismiss();
            }
        });
        //toast("请选择病人");
    }


}
