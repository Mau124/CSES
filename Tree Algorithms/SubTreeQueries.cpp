#include<bits/stdc++.h>
using namespace std;
 
#define N 200001
#define ll long long
 
vector<int> adj[N];
ll n, q, a, b, pos[N], sub[N], node_value[N], val[N];
 
int it = 0;
 
class SegTree {
public:
    ll sum;
    int leftmost, rightmost;
    SegTree *lChild, *rChild;
 
    SegTree(int l, int r, ll a[]) {
        leftmost = l;
        rightmost = r;
 
        if(leftmost == rightmost) {
            sum = a[leftmost];
            return;
        } else {
            int m = leftmost + (rightmost-leftmost)/2;
            lChild = new SegTree(l, m, a);
            rChild = new SegTree(m+1, r, a);
            recalc();
        }
    }
 
    void update(int index, int newVal) {
        if(leftmost == rightmost) {
            sum = newVal;
            return;
        } 
 
        if(index <= lChild->rightmost) 
            lChild->update(index, newVal);
        else
            rChild->update(index, newVal);
 
        recalc();
    }
 
    ll sumRange(int l, int r) {
        // Case 1: [l, r] does not overlap at all [leftmost, rightmost]
        if(l > rightmost || r < leftmost) 
            return 0;
 
        // Case 2: [l, r] overlaps completeley [leftmost, rightmost]
        if(l <= leftmost && r >= rightmost) 
            return sum;
 
        // Case 3: [l, r] overlaps partially [leftmost, rightmost]
        // Need to shrink interval
        return lChild->sumRange(l, r) + rChild->sumRange(l, r);
    }
 
    void recalc() {
        if(leftmost == rightmost) return;
 
        sum = lChild->sum + rChild->sum;        
    }
};
 
void dfs(int v, int p) {
    sub[v] = 1;
    node_value[it] = val[v];
    pos[v] = it++;
     
    for(int u: adj[v]) {
        if(u != p) {
            dfs(u, v);        
            sub[v] += sub[u];
         }
    }
}
 
void print(ll a[], int n) {
    for(int i=1; i<=n; ++i) 
        cout << a[i] << " ";
    cout << "\n";
}
 
void printValues(int n) {
    print(pos, n);
    print(sub, n);
    print(node_value, n);
}
 
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
 
    // Read number of nodes and number of queries
    cin >> n >> q;
    
    // Read values of each node
    for(int i=1; i<=n; ++i) {
        cin >> val[i];
    }
 
    // Read connections between nodes (edges)
    for(int i=0; i<n-1; ++i) {
        cin >> a >> b;
        adj[a].push_back(b);
        adj[b].push_back(a);
    }
    
    // Construct sums for each subtree
    it = 1;
    dfs(1, -1);
    //printValues(n);
 
    SegTree segment_tree = SegTree(1, n, node_value);
 
    // Process each query
    
    for(int i=0; i<q; ++i) {
        cin >> a;
        if(a == 1) {
            cin >> a >> b;
            segment_tree.update(pos[a], b);   
        } else {
            cin >> a;
            int l = pos[a];
            int r = l + sub[a] - 1;
 
            cout << segment_tree.sumRange(l, r) << "\n";
        }
    }
    
 
    return 0;
}
