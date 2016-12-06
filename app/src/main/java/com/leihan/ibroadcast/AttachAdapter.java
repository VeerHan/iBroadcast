package com.leihan.ibroadcast;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lei.han
 * @time 2016/12/5 下午9:27
 */
public class AttachAdapter extends BaseAdapter {

    private List<Attach> list;
    private int maxCount; // 图片的最大数量
    private boolean showInfo; // 是否显示图片文字说明
    private Attach addAttach; // 用于图片数量不满足时"添加图片"的item
    private Context context;

    public AttachAdapter(Context context, int maxCount, boolean showInfo) {
        this.context = context;
        this.maxCount = maxCount;
        this.showInfo = showInfo;
        this.list = new ArrayList<>();
        addAttach = new Attach();
        addAttach.fileType = Constants.FILE_TYPE_ADD_ICON;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Attach getItem(int position) {
        if (position < 0 || position >= getCount()) {
            return null;
        }
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 添加一个元素
     */
    public void add(Attach attach) {
        if (attach == null) {
            return;
        }
        list.add(attach);
        notifyDataSetChanged();
    }

    /**
     * 删除一个元素
     */
    public void remove(Attach attach) {
        if (attach == null) {
            return;
        }
        if (list.contains(attach)) {
            list.remove(attach);
            notifyDataSetChanged();
        }
    }


    /**
     * 设置数据源
     */
    public void setDataSet(List<Attach> dataSet) {
        if (dataSet == null) {
            return;
        }
        list.clear();
        list.addAll(dataSet);
        notifyDataSetChanged();
    }

    @Override
    public void notifyDataSetChanged() {
        if (list.contains(addAttach)) {
            list.remove(addAttach);
        }
        if (list.size() < maxCount) {
            list.add(addAttach);
        }
        super.notifyDataSetChanged();
    }

    /**
     * 获取图片类型集合，用于图片预览
     *
     * @return
     */
    public List<Attach> getImgList() {
        List<Attach> attachList = new ArrayList<>();
        for (Attach attach : list) {
            if (attach.fileType == Constants.FILE_TYPE_IMG) {
                // adapter筛选出所有图片类型的集合List，计算出当前图片在List中的position
                attachList.add(attach);
            }
        }
        return attachList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_listview_attach, null);
            viewHolder = new ViewHolder();
            viewHolder.ivImg = (ProImageView) convertView.findViewById(R.id.iv_img);
            viewHolder.ivIcon = (ImageView) convertView.findViewById(R.id.iv_icon);
            viewHolder.etInfo = (EditText) convertView.findViewById(R.id.et_info);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Attach attach = list.get(position);

        // 获取任务列表时，如果state为默认，并且isTemp为true，则代表附件处于上传失败状态
        if (attach.isTemp && attach.state == Constants.FILE_STATE_DEFAULT) {
            attach.state = Constants.FILE_STATE_ERROR;
        }

        switch (attach.state) {
            // 默认状态
            case Constants.FILE_STATE_DEFAULT:
                break;
            // 正在上传
            case Constants.FILE_STATE_LOADING:
                break;
            // 上传失败
            case Constants.FILE_STATE_ERROR:
                break;
        }


        return convertView;
    }


    public static class ViewHolder {
        ProImageView ivImg;
        ImageView ivIcon;
        EditText etInfo;
    }
}