#include<iostream>
#include<vector>
using namespace std;
 
unsigned long long getProducts(long long time, vector<long long> machines) {
    unsigned long long products = 0;
    for(int i=0; i<machines.size(); ++i) {
        //cout << "Value of time/machines[i]. Time:: " << time << " Machines:: " << machines[i] << " Time//Machines:: " << time/machines[i] << endl;
        products = products + (time/machines[i]);
    }
    return products;
}
 
int main() {
    long long n, t, tmp;
    cin >> n >> t;
    vector<long long> machines(n);
 
    long long maxTime = 0;
    for(int i=0; i<n; ++i) {
        cin >> tmp;
        maxTime = max(maxTime, tmp);
        machines[i] = tmp;
    }
 
    // Binary search
    long long l = 0, r = maxTime*t;
    long long ans = 0;
    //cout << "Max time " << r << endl;
    while(l<r-1) {
        // We check if we can do it with m machines
        long long m = l + (r-l)/2;
        //cout << "Value of m " << m << " with products " << getProducts(m, machines) << endl;
        if(getProducts(m, machines) >= t) {
            r = m;
        } else {
            l = m;
        }
    }
 
    cout << r << "\n";
 
    return 0;
}
