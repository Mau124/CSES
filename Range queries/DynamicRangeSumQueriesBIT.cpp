// Dynamic range queries using BIT

#include<bits/stdc++.h>
using namespace std;

using ll = long long;
const int N = 2e5+5;

ll arr[N];
int n, q;

// Class for making the BIT
struct BIT {
    vector<ll> T;

    BIT() {
        // Create indexed binary tree      
        T.assign(N, 0);
        for(int i=1; i<=n; ++i) {
            update(i, arr[i]);
        }
    }

    ll sum(ll it) {
        ll ans = 0; 
        while(it > 0) {
            ans += T[it];
            
            // Remove last set bit from it
            it -= it & (-it);
        }
        return ans;
    }

    void update(ll it, ll newVal) {
        while (it <= n) {
            T[it] += newVal; 

            // Add last set bit from index
            it += it & (-it); 
        } 
    } 

    void print() {
        for(int i=1; i<=n; ++i)
            cout << T[i] << " ";
        cout << "\n";
    }
};

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> n >> q;

    // Using BIT, array has to be one indexed
    for(int i=1; i<=n; ++i) 
        cin >> arr[i];
   
    // Construct the BIT
    BIT tree = BIT();

    // Print BIT
    // tree.print();

    for(int qq=0; qq<q; ++qq) {
        int opc, a, b;

        cin >> opc >> a >> b;

        if(opc == 1) {
            tree.update(a, -arr[a]);
            arr[a] = b;
            tree.update(a, arr[a]);
        }
        else
            cout << tree.sum(b) - tree.sum(a-1) << "\n";
    }

    // tree.print();

    return 0;
}
