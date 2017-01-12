package com.company;

/**
 * Created by ryan on 1/10/17.
 */


public class HashTable<Key, Value> {
    private HashNode<Key, Value>[] nodes;

    public HashTable(int size){
        nodes = new HashNode[size];
    }

    private int getIndex(Key key){
        int hash = key.hashCode() % nodes.length;
        if(hash < 0){
            hash += nodes.length;
        }
        return hash;
    }

    public Value insert(Key key, Value data){
        int hash = getIndex(key);

        // Lets check if same key already exists and if so lets update it with the new value
        for(HashNode<Key,Value> node = nodes[hash]; node != null; node = node.next){
            if((hash == node.hash) && key.equals(node.key)){
                Value oldData = node.data;
                node.data = data;
                return oldData;
            }
        }

        // Lets add the new hash node created below to the start of linked list at nodes[hash] position
        HashNode<Key,Value> node = new HashNode<Key,Value>(key, data, nodes[hash], hash);
        nodes[hash] = node;

        return null;
    }


}
