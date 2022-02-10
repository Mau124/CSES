#include<bits/stdc++.h>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, tmp;
    cin >> n;
    vector<int> ans(n+1);
    stack<pair<int, int>> st;

    for(int i=1; i<=n; ++i) {
        cin >> tmp;

        if(st.empty()) {
            ans[i] = 0;
        } else {
            while(!st.empty() && tmp <= st.top().second) {
                st.pop(); 
            }

            ans[i] = (st.empty()) ? 0 : st.top().first;
        }

        st.push({i, tmp});
    }

    for(int i=1; i<=n; ++i) {
        cout << ans[i] << " ";
    }

    cout << "\n";

    return 0;
}
