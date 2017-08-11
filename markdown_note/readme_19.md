联系客服

```java

    /**
     * 联系客服
     */
    private void contactService() {

        //1. 获取客服号码:
        final String phone = tvMorePhone.getText().toString().trim();

        //2. dialog

        new AlertDialog.Builder(MoreFragment.this.getActivity())
                .setMessage("拨打客服电话:" + phone)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //1. 使用隐式意图启动intent
                        Intent intent = new Intent(Intent.ACTION_CALL);
                        //2.
                        intent.setData(Uri.parse("tel:" + phone));
                        if (ActivityCompat.checkSelfPermission(MoreFragment.this.getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }
                        MoreFragment.this.getActivity().startActivity(intent);

                    }
                })
                .setNegativeButton("取消",null)
                .show();
    }
```



