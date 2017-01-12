package com.company;

/**
 * Created by ryan on 1/10/17.
 */
public class HashNode<Key,Value> {
    final Key key;
    Value data;
    HashNode<Key,Value> next;
    final int hash;

    public HashNode(Key k, Value v, HashNode<Key,Value> n, int h){
        key = k;
        data = v;
        next = n;
        hash = h;
    }
}