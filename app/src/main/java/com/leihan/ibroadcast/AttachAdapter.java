package com.leihan.ibroadcast;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author lei.han
 * @time 2016/12/5 下午9:27
 */
public class AttachAdapter extends BaseAdapter {

    private static final String TAG = AttachAdapter.class.getSimpleName();
    private List<Attach> list;
    private int maxCount;
    private Context context;

    public AttachAdapter(Context context, int maxCount) {
        this.context = context;
        this.list = new ArrayList<>();
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
     * 根据元素的id查找对应元素
     */
    public Attach queryByUid(String uid) {
        Iterator<Attach> iterator = list.iterator();
        while (iterator.hasNext()) {
            Attach subTask = iterator.next();
            if (uid.equals(subTask.uid)) {
                return subTask;
            }
        }
        return null;
    }

    /**
     * 根据元素的id删除对应元素
     */
    public void deleteByUid(String uid) {
        Iterator<Attach> iterator = list.iterator();
        while (iterator.hasNext()) {
            Attach attach = iterator.next();
            if (uid.equals(attach.uid)) {
                iterator.remove();
                notifyDataSetChanged();
                return;
            }
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

    /**
     * 获取数据源
     */
    public List<Attach> getDataSet() {
        return list;
    }

    /**
     * 获取Fs图片类型文件的集合，用于图片预览
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
            // convertView = View.inflate(context, , null);
            viewHolder = new ViewHolder();
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
    }
}