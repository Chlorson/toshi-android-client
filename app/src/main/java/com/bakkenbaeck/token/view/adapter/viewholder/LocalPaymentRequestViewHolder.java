package com.bakkenbaeck.token.view.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bakkenbaeck.token.R;
import com.bakkenbaeck.token.model.sofa.PaymentRequest;
import com.bakkenbaeck.token.util.EthUtil;
import com.bakkenbaeck.token.view.BaseApplication;

public final class LocalPaymentRequestViewHolder extends RecyclerView.ViewHolder {

    private View localView;
    private View remoteView;
    private TextView localMessageText;
    private TextView remoteMessageText;
    private TextView localRequestedAmount;
    private TextView remoteRequestedAmount;
    private TextView localSecondaryAmount;
    private TextView remoteSecondaryAmount;

    public LocalPaymentRequestViewHolder(final View v) {
        super(v);
        this.localView = v.findViewById(R.id.local);
        this.remoteView = v.findViewById(R.id.remote);
        this.localMessageText = (TextView) v.findViewById(R.id.local_message);
        this.remoteMessageText = (TextView) v.findViewById(R.id.remote_message);
        this.localRequestedAmount = (TextView) v.findViewById(R.id.local_requested_amount);
        this.remoteRequestedAmount = (TextView) v.findViewById(R.id.remote_requested_amount);
        this.localSecondaryAmount = (TextView) v.findViewById(R.id.local_eth_amount);
        this.remoteSecondaryAmount = (TextView) v.findViewById(R.id.remote_eth_amount);

    }

    public void setTxRequest(final PaymentRequest request, final boolean sentByLocal) {
        if (sentByLocal) {
            this.localView.setVisibility(View.VISIBLE);
            this.remoteView.setVisibility(View.GONE);
            this.localRequestedAmount.setText(request.getLocalPrice());
            final String ethAmount = String.format(
                    BaseApplication.get().getResources().getString(R.string.eth_amount),
                    EthUtil.weiToEthString(request.getValue()));
            this.localSecondaryAmount.setText(ethAmount);
        } else {
            this.remoteView.setVisibility(View.VISIBLE);
            this.localView.setVisibility(View.GONE);
            this.remoteRequestedAmount.setText(request.getLocalPrice());
            final String ethAmount = String.format(
                    BaseApplication.get().getResources().getString(R.string.eth_amount),
                    EthUtil.weiToEthString(request.getValue()));
            this.remoteSecondaryAmount.setText(ethAmount);
        }
    }
}